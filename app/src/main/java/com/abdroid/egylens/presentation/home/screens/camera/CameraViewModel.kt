package com.abdroid.egylens.presentation.home.screens.camera

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.domain.repository.CameraRepository
import com.abdroid.egylens.util.Constants.BASE_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraRepository: CameraRepository
): ViewModel() {


    fun onTakePhoto(
        controller: LifecycleCameraController
    ) {
        viewModelScope.launch {
            cameraRepository.takePhoto(controller)
        }
    }
    fun uploadImage(
        context: Context,
        uri: Uri,
        controller: LifecycleCameraController,
        onSuccess: (String) -> Unit,
        onFailure: () -> Unit
    ) {
        val client = OkHttpClient()
        val mediaType = "image/*".toMediaType() // Change media type according to your image type
        val inputStream = context.contentResolver.openInputStream(uri)
        val requestBody = inputStream?.readBytes()?.toRequestBody(mediaType)

        val body = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("image", uri.path, requestBody!!)
            .build()

        val request = Request.Builder()
            .url(BASE_URL)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Use Handler to post to the main thread
                Handler(Looper.getMainLooper()).post {
                    onFailure.invoke()
                    Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()

                try {
                    val jsonResponse = JSONObject(responseBody.toString())
                    val resultString = jsonResponse.getString("result")
                    onSuccess.invoke(resultString)
                } catch (e: JSONException) {
                    // Use Handler to post to the main thread
                    Handler(Looper.getMainLooper()).post {
                        onFailure.invoke()
                        Toast.makeText(context, "No detection or Failed to connect to the server", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }


    fun fetchDataByName(name: String, statuesRef: DatabaseReference, callback: (Statue?) -> Unit) {
        if (name.trim().isNotEmpty()) {
            statuesRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.mapNotNull { snapshot ->
                        snapshot.getValue(Statue::class.java)
                    }.firstOrNull { statue ->
                        statue.name.equals(name, ignoreCase = true)
                    }?.let { foundStatue ->
                        callback(foundStatue)
                        return
                    }
                    callback(null)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null)
                }
            })
        } else {
            callback(null)
        }
    }

}






















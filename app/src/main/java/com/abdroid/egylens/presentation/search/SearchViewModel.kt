package com.abdroid.egylens.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.abdroid.egylens.domain.manager.AuthRepository
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.presentation.home.HomeState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {

     val statuesList: MutableList<Statue> = mutableListOf()

     fun fetchData(search: String, statuesRef: DatabaseReference) {
          if (search.trim().isNotEmpty()) {
               statuesRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                         statuesList.clear()
                         snapshot.children.mapNotNull { snapshot ->
                              snapshot.getValue(Statue::class.java)
                         }.forEach { statue ->
                              if (statue.name.contains(search, ignoreCase = true) ||
                                   statue.desc.contains(search, ignoreCase = true)
                              ) {
                                   statuesList.add(statue)
                              }
                         }
                         statuesList.sortBy {
                              kotlin.math.abs(it.name.length - search.length)
                         }
                    }

                    override fun onCancelled(error: DatabaseError) {
                         // Handle error
                    }
               })
          }
     }

}
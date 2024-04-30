package com.abdroid.egylens.presentation.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.abdroid.egylens.domain.manager.AuthRepository
import com.abdroid.egylens.presentation.home.HomeState
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {


    val currentUser: FirebaseUser?
        get() = repository.currentUser

    fun signOut(){
        repository.signOut()
    }

}
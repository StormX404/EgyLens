package com.abdroid.egylens.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.abdroid.egylens.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    var state = mutableStateOf(HomeState())
        private set

    val currentUser: FirebaseUser?
        get() = repository.currentUser



}
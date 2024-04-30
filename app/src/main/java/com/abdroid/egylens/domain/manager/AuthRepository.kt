package com.abdroid.egylens.domain.manager

import com.abdroid.egylens.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun resetPassword(email: String): Flow<Resource<Void>>
    fun registerUser(name : String ,email: String, password: String,confirmPassword:String): Flow<Resource<AuthResult>>
    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
    fun signOut()

    //fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>


}
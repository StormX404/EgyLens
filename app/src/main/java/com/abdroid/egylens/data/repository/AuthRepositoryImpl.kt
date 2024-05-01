package com.abdroid.egylens.data.repository

import com.abdroid.egylens.domain.repository.AuthRepository
import com.abdroid.egylens.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser


    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }

    }

    override fun resetPassword(email: String): Flow<Resource<Void>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.sendPasswordResetEmail(email).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }

    }
    override fun registerUser(name: String, email: String, password: String, confirmPassword: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())

            if (name.isBlank()) {
                emit(Resource.Error("Name cannot be empty or contain only spaces"))
                return@flow
            }
            // Check if email is valid
            if (!isValidEmail(email)) {
                emit(Resource.Error("Invalid email format"))
                return@flow
            }

            // Check if password meets criteria
            if (!isValidPassword(password)) {
                emit(Resource.Error("Password should be at least 6 characters , one letter and one number"))
                return@flow
            }

            if (password != confirmPassword) {
                emit(Resource.Error("Passwords not matching"))
            } else {
                try {
                    val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                    result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
                    sendEmailVerification() // Send email verification
                    emit(Resource.Success(result))
                } catch (e: FirebaseAuthUserCollisionException) {
                    emit(Resource.Error("This email is already in use"))
                } catch (e: Exception) {
                    emit(Resource.Error("${e.message}"))
                }
            }
        }.catch { e ->
            emit(Resource.Error("Try to use another email: ${e.message}"))
        }
    }

    private fun sendEmailVerification() {
        val user = firebaseAuth.currentUser
        user?.sendEmailVerification()
    }

    private fun isValidEmail(email: String): Boolean {
        // Regex pattern for email validation
        val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(pattern.toRegex())
    }

    private fun isValidPassword(password: String): Boolean {
        // Add your password criteria here, for example, minimum length
        val pattern = "(?=.*[a-z])(?=.*\\d).{6,}"
        return password.matches(pattern.toRegex())
    }

    override fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun signOut() {
        try {
            firebaseAuth.signOut()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }
}
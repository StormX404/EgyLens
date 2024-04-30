package com.abdroid.egylens.presentation.signInFlow.forgotPassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdroid.egylens.domain.manager.AuthRepository
import com.abdroid.egylens.presentation.signInFlow.signIn.GoogleSignInState
import com.abdroid.egylens.presentation.signInFlow.signIn.SignInState
import com.abdroid.egylens.util.Resource
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {


    val _resetState = Channel<ForgotPasswordState>()
    val resetState = _resetState.receiveAsFlow()




    fun resetPassword(email: String) = viewModelScope.launch {
        repository.resetPassword(email).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _resetState.send(ForgotPasswordState(isSuccess = "Check your email") )
                }
                is Resource.Loading -> {
                    _resetState.send(ForgotPasswordState(isLoading = true))
                }
                is Resource.Error -> {

                    _resetState.send(ForgotPasswordState(isError = result.message))
                }
            }

        }
    }
}
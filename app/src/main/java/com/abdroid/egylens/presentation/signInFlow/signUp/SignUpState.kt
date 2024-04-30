package com.abdroid.egylens.presentation.signInFlow.signUp


data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = ""

)
package com.abdroid.egylens.presentation.signInFlow.forgotPassword


data class ForgotPasswordState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = "",
)
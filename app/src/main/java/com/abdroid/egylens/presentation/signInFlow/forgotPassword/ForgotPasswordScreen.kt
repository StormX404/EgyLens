package com.abdroid.egylens.presentation.signInFlow.forgotPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.CustomDialog
import com.abdroid.egylens.presentation.common.EmailTextField
import com.abdroid.egylens.ui.theme.notoFont
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val successDialog = remember { mutableStateOf(false) }

    if (successDialog.value) {
        CustomDialog(title = "Check Your Email",
            desc = "we sent an email to reset Your password",
            onDismiss = {
                successDialog.value = false
                navController.popBackStack()
            }
        )
    }

    Column (
        modifier = Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Top
        ){
        var email by rememberSaveable { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        val state = viewModel.resetState.collectAsState(initial = null)

        Spacer(modifier = Modifier.size(20.dp))
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
                    .background(colorResource(id = R.color.text_field_bg))
                    .clickable(
                        onClick = {
                            navController.popBackStack()
                        },
                    ),
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "",
                tint = colorResource(id = R.color.main_text),
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Forgot Password",
                fontFamily = notoFont,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.main_text)
            )
            Text(
                text = "Enter your email account to reset your password.",
                fontFamily = notoFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.second_text)
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        EmailTextField(
            hintValue = "Example@example.com",
            leadingIcon = painterResource(id = R.drawable.sms),
            email = email,
            onEmailChange = { newEmail -> email = newEmail }

            //errorStatus = loginViewModel.loginUIState.value.emailError
        )
        Spacer(modifier = Modifier.size(30.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(14.dp)),
            //.padding(8.dp),
            onClick = {
                scope.launch {
                    viewModel.resetPassword(email)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.main_button),
                contentColor = colorResource(id = R.color.button_text)
            ),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            Text(
                text = "Reset Password",
                fontFamily = notoFont,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.button_text)
            )
        }
        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    successDialog.value = true
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev() {
    ForgotPasswordScreen(navController = rememberNavController())
}
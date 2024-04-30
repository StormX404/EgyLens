package com.abdroid.egylens.presentation.signInFlow.signUp

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.abdroid.egylens.presentation.common.Loader
import com.abdroid.egylens.presentation.common.NameTextField
import com.abdroid.egylens.presentation.common.PasswordTextField
import com.abdroid.egylens.ui.theme.notoFont
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var isPasswordOpen by remember { mutableStateOf(false) }
    var isRePasswordOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signUpState.collectAsState(initial = null)

    val successDialog = remember { mutableStateOf(false) }

    if (successDialog.value) {
        CustomDialog(title = "Email created successfully",
            desc = "we sent a verification email to You",
            onDismiss = {
                successDialog.value = false
                navController.popBackStack()
            }
        )
    }

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
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
        Spacer(modifier = Modifier.size(10.dp))
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Register Account",
                fontFamily = notoFont,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.main_text)
            )
            Text(
                text = "Fill your details or continue with social media.",
                fontFamily = notoFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.second_text)
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
            //.padding(horizontal = 10.dp)
            , horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = "Your First Name",
                fontFamily = notoFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )
            NameTextField(
                hintValue = "Enter your first name",
                leadingIcon = painterResource(id = R.drawable.profile_outlined),
                name = name,
                onNameChange = { newName -> name = newName }

                //errorStatus = loginViewModel.loginUIState.value.emailError
            )
        }


        Spacer(modifier = Modifier.size(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
            //.padding(horizontal = 10.dp)
            , horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = "Email Address",
                fontFamily = notoFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )
            EmailTextField(
                hintValue = "Example@example.com",
                leadingIcon = painterResource(id = R.drawable.sms),
                email = email,
                onEmailChange = { newEmail -> email = newEmail }

                //errorStatus = loginViewModel.loginUIState.value.emailError
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
            //.padding(horizontal = 10.dp)
            , horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = "Password",
                fontFamily = notoFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )
            PasswordTextField(
                hintValue = "Enter your password",
                leadingIcon = painterResource(id = R.drawable.security_safe),
                password = password,
                onPasswordChange = { newPassword -> password = newPassword },
                isPasswordOpen = isPasswordOpen,
                onTogglePasswordVisibility = { isPasswordOpen = !isPasswordOpen }
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
            //.padding(horizontal = 10.dp)
            , horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = "Confirm Password",
                fontFamily = notoFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )
            PasswordTextField(
                hintValue = "Re enter your password",
                leadingIcon = painterResource(id = R.drawable.security_safe),
                password = confirmPassword,
                onPasswordChange = { newConfirmPassword -> confirmPassword = newConfirmPassword },
                isPasswordOpen = isRePasswordOpen,
                onTogglePasswordVisibility = { isRePasswordOpen = !isRePasswordOpen }
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(14.dp)),
            //.padding(8.dp),
            onClick = {
                    scope.launch {
                        viewModel.registerUser(name, email, password, confirmPassword)
                    }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.main_button),
                contentColor = colorResource(id = R.color.button_text)
            ),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            if (state.value?.isLoading == true) {
                Loader()
            }
            Text(
                text = "Register",
                fontFamily = notoFont,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.button_text)
            )

        }
        //Spacer(modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.size(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already Have Account?",
                fontFamily = notoFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.second_text),
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clickable(
                        onClick = { navController.popBackStack() },
                    ),
                text = "Log In",
                fontFamily = notoFont,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )
        }
    }


    LaunchedEffect(key1 = Pair(state.value?.isSuccess, state.value?.isError)) {
        scope.launch {
            val isSuccess = state.value?.isSuccess
            val isError = state.value?.isError

            if (isSuccess == true) {
                // Show success dialog
                successDialog.value = true
            }

            if (isError?.isNotBlank() == true) {
                // Show error toast
                Toast.makeText(context, isError, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInPrev() {
    SignUpScreen(navController = rememberNavController())
}
package com.abdroid.egylens.presentation.signInFlow.signIn

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.EmailTextField
import com.abdroid.egylens.presentation.common.Loader
import com.abdroid.egylens.presentation.common.PasswordTextField
import com.abdroid.egylens.ui.theme.notoFont
import com.abdroid.egylens.util.Constants.SERVER_CLIENT
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken!!, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)
    val googleState = viewModel.googleState.value
    
    Column (
        modifier = Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center,

    ){
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Welcome Back!",
                fontFamily = notoFont,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.main_text)
            )
            Text(
                text = "Please sign in your account.",
                fontFamily = notoFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.second_text)
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                //.padding(horizontal = 10.dp)
            , horizontalAlignment = Alignment.Start)
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
        Spacer(modifier = Modifier.size(15.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
            //.padding(horizontal = 10.dp)
            , horizontalAlignment = Alignment.Start)
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
        Row (modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.End){
            Text(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            navController.navigate("forgotPasswordScreen")
                        },
                    ),
                text = "Forgot password?",
                fontFamily = notoFont,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(14.dp)),
            //.padding(8.dp),
            onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)
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
                modifier = Modifier.padding(start = 4.dp),
                text = "Login",
                fontFamily = notoFont,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.button_text)
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(14.dp)),
            //.padding(8.dp),
            onClick = {
                    val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(SERVER_CLIENT)
                        .requestEmail()
                        .build()

                    val googleSingInClient = GoogleSignIn.getClient(context, gso)

                    launcher.launch(googleSingInClient.signInIntent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.text_field_bg),
                contentColor = colorResource(id = R.color.main_text)
            ),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(26.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Sign In with Google",
                fontFamily = notoFont,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )

        }
        Spacer(modifier = Modifier.size(40.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "New user?",
                fontFamily = notoFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.second_text),
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clickable(
                        onClick = {
                            navController.navigate("signUpScreen")
                        },
                    ),
                text = "Create account",
                fontFamily = notoFont,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )
        }
        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true && viewModel.currentUser?.isEmailVerified == true) {
                    //val success = state.value?.isSuccess
                    navController.popBackStack()
                    navController.navigate("homeNavigation")
                    //Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                }
            }
        }

        LaunchedEffect(key1 = Pair(state.value?.isError, googleState.success)) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
                } else if (googleState.success != null) {
                    navController.popBackStack()
                    navController.navigate("homeNavigation")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPrev() {
    SignInScreen(navController = rememberNavController())
}



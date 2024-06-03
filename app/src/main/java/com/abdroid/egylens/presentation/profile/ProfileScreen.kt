
package com.abdroid.egylens.presentation.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.CustomTopAppBar
import com.abdroid.egylens.presentation.common.LogOutDialog
import com.abdroid.egylens.presentation.profile.components.ProfileCard
import com.abdroid.egylens.presentation.navGraph.Route
import com.abdroid.egylens.ui.theme.notoFont
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ProfileScreen(
    navController :NavHostController,
    viewModel: ProfileViewModel = hiltViewModel(),
    ) {
    val successDialog = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (successDialog.value) {
        LogOutDialog(title = "Logout",
            desc = "Are you sure you want log out?",
            onDismiss = {
                successDialog.value = false
            },
            onContinue = {
                successDialog.value = false
                scope.launch {
                    viewModel.signOut()

                }
                navController.navigate(Route.EntryNavigation.route) {
                    // Specify the navigation options and set the popUpTo parameter
                    popUpTo(Route.HomeNavigation.route) {
                        inclusive = true // Include the start destination in popping
                    }
                }
            }
        )
    }

    Column(
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding(),
        //horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Top
    ) {
        CustomTopAppBar(text = "Profile", height = 60)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.divider)
        )
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (viewModel.currentUser?.photoUrl != null) {
                AsyncImage(
                    model = viewModel.currentUser?.photoUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            } else {
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.profile_screen_icon),
                    contentDescription = null,
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = viewModel.currentUser?.displayName ?: "",
                    fontFamily = notoFont,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.main_text)
                )
                Text(
                    text = viewModel.currentUser?.email ?: "",
                    fontFamily = notoFont,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.second_text)
                )
            }

        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.divider)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            ProfileCard(icon = painterResource(id = R.drawable.profile), text = "Personal Information" , onClick = {
                navController.navigate(Route.PersonalInfoScreen.route)
            })

            /*ProfileCard(icon = painterResource(id = R.drawable.bookmark_bulk), text = "Bookmarks", onClick = {})

            ProfileCard(icon = painterResource(id = R.drawable.translate), text = "Change Language", onClick = {})*/

            /*ThemeProfileCard(
                icon = painterResource(id = R.drawable.moon),
                text = "Dark Mode", darkTheme = darkTheme,
                onThemeUpdated = {
                    darkTheme = !darkTheme }
            )*/

            ProfileCard(icon = painterResource(id = R.drawable.message_question), text = "FAQ", onClick = {
                navController.navigate(Route.FaqScreen.route)
            })

            ProfileCard(icon = painterResource(id = R.drawable.info_circle), text = "About", onClick = {
                navController.navigate(Route.AboutScreen.route)
            })

            ProfileCard(icon = painterResource(id = R.drawable.logout), text = "Logout", onClick = {
                successDialog.value = true
            })
        }
    }

}



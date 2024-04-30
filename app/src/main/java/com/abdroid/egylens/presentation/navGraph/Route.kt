package com.abdroid.egylens.presentation.navGraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object SignInScreen : Route(route = "signInScreen")

    object SignUpScreen : Route(route = "signUpScreen")

    object ForgotPasswordScreen : Route(route = "forgotPasswordScreen")

    object HomeScreen : Route(route = "homeScreen")

    object FavoritesScreen : Route(route = "favoritesScreen")

    object SearchScreen : Route(route = "searchScreen")

    object ProfileScreen : Route(route = "profileScreen")

    object PreScanScreen : Route(route = "preScanScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object EntryNavigation : Route(route = "entryNavigation")

    object HomeNavigation : Route(route = "homeNavigation")

    object CameraScreen : Route(route = "cameraScreen")

    object FaqScreen : Route(route = "faqScreen")

    object DetailsScreen : Route(route = "detailsScreen")

}
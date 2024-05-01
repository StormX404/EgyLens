package com.abdroid.egylens.presentation.navGraph

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.abdroid.egylens.R
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.presentation.common.AppBottomNavigation
import com.abdroid.egylens.presentation.common.BottomNavigationItem
import com.abdroid.egylens.presentation.details.DetailsScreen
import com.abdroid.egylens.presentation.favorites.FavoritesScreen
import com.abdroid.egylens.presentation.signInFlow.forgotPassword.ForgotPasswordScreen
import com.abdroid.egylens.presentation.home.HomeScreen
import com.abdroid.egylens.presentation.home.screens.camera.CameraScreen
import com.abdroid.egylens.presentation.signInFlow.signIn.SignInScreen
import com.abdroid.egylens.presentation.signInFlow.signUp.SignUpScreen
import com.abdroid.egylens.presentation.onBoardingScreen.OnBoardingScreen
import com.abdroid.egylens.presentation.onBoardingScreen.OnBoardingViewModel
import com.abdroid.egylens.presentation.profile.ProfileScreen
import com.abdroid.egylens.presentation.profile.screens.FaqScreen
import com.abdroid.egylens.presentation.home.screens.preScan.PreScanScreen
import com.abdroid.egylens.presentation.search.SearchScreen
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun NavGraph(
    startDestination: String
) {
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            iconOutlined = R.drawable.home_outlined,
            filled = R.drawable.home_bold,
            text = "Home"
        ),
        BottomNavigationItem(
            iconOutlined = R.drawable.search_normal_outlined,
            filled = R.drawable.search_normal,
            text = "Search"
        ),
        BottomNavigationItem(
            iconOutlined = R.drawable.heart_outlined,
            filled = R.drawable.heart_bold,
            text = "Favorites"
        ),
        BottomNavigationItem(
            iconOutlined = R.drawable.profile_outlined,
            filled = R.drawable.profile_bold,
            text = "Profile"
        ),
    )

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.FavoritesScreen.route -> 2
        Route.ProfileScreen.route -> 3
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.FavoritesScreen.route ||
                backStackState?.destination?.route == Route.ProfileScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                AppBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Route.HomeScreen.route)
                            1 -> navigateToTab(navController, Route.SearchScreen.route)
                            2 -> navigateToTab(navController, Route.FavoritesScreen.route)
                            3 -> navigateToTab(navController, Route.ProfileScreen.route)
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(bottom = bottomPadding),
        ) {
            //AppStarting
            navigation(
                route = Route.AppStartNavigation.route,
                startDestination = Route.OnBoardingScreen.route,
            ) {
                composable(route = Route.OnBoardingScreen.route) {
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(onEvent = viewModel::onEvent)
                }

            }
            //Entry
            navigation(
                route = Route.EntryNavigation.route,
                startDestination = "signInScreen"
            ) {
                composable(route = Route.SignInScreen.route) {
                    SignInScreen(navController)
                }
                composable(route = Route.ForgotPasswordScreen.route) {
                    ForgotPasswordScreen(navController)
                }
                composable(route = Route.SignUpScreen.route) {
                    SignUpScreen(navController)
                }
            }

            navigation(
                route = Route.HomeNavigation.route,
                startDestination = Route.HomeScreen.route
            ) {
                composable(route = Route.HomeScreen.route) {
                    HomeScreen(
                        navController,
                        navigateToSearch = { navigateToTab(navController, "searchScreen") }

                    )
                }
                composable(route = Route.SearchScreen.route) {
                    OnBackClickStateSaver(navController = navController)
                    SearchScreen(navController)
                }
                composable(route = Route.FavoritesScreen.route) {
                    OnBackClickStateSaver(navController = navController)
                    FavoritesScreen()
                }
                composable(route = Route.ProfileScreen.route) {
                    OnBackClickStateSaver(navController = navController)
                    ProfileScreen(navController)
                }
                composable(route = Route.PreScanScreen.route) {
                    PreScanScreen(navController)
                }
                composable(route = Route.CameraScreen.route) {
                    CameraScreen( navController , context = LocalContext.current)
                }
                composable(route = Route.FaqScreen.route) {
                    FaqScreen()
                }

                composable(route = Route.DetailsScreen.route) {
                    navController.previousBackStackEntry?.savedStateHandle?.get<Statue?>("statue")
                        ?.let { statue ->
                            DetailsScreen(
                                navController,
                                statue = statue,
                            )
                        }

                }


            }
        }
    }
}


@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(navController, Route.HomeScreen.route)
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

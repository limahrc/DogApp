package com.encomp.dogapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.encomp.dogapp.ui.screens.breeds.BreedsScreen
import com.encomp.dogapp.ui.screens.detail.DetailScreen
import com.encomp.dogapp.ui.screens.home.HomeScreen

@Composable
fun DogAppNavigator(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onDogBreedsButtonClicked = { navController.navigate("breeds") }
            )
        }
        composable("breeds") {
            BreedsScreen(
                onBreedClicked = { breed ->
                    navController.navigate(
                        route = "breedDetail/$breed",
                        navOptions = NavOptions
                            .Builder()
                            .setPopUpTo("breeds", true)
                            .build()
                    )
                },
                onBackButtonClicked = { navController.popBackStack() })
        }
        composable("breedDetail/{breed}") { backStackEntry ->
                val breed = backStackEntry.arguments?.getString("breed").orEmpty()
                DetailScreen(
                    breed = breed,
                    onBackButtonClicked = { navController.popBackStack() }
                )
        }
    }
}
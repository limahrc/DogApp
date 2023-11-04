package com.encomp.dogapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.encomp.dogapp.ui.screens.breeds.BreedsScreen
import com.encomp.dogapp.ui.screens.home.HomeScreen

@Composable
fun DogAppNavigator(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onRandomDogButtonClicked = { navController.navigate("randomDog") },
                onDogBreedsButtonClicked = { navController.navigate("breeds") }
            )
        }
        composable("breeds") {
            BreedsScreen(
                onBreedClicked = { breed -> navController.navigate("breedDetail/$breed") },
                onBackButtonClicked = { navController.popBackStack() })
        }
        composable("breedDetail") {

        }
    }
}
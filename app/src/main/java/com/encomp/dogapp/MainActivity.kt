package com.encomp.dogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.encomp.dogapp.ui.navigation.DogAppNavigator
import com.encomp.dogapp.ui.theme.DogAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogAppTheme {
                val navController = rememberNavController()

                DogAppNavigator(navController = navController)
            }
        }
    }
}
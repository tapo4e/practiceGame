package com.example.practicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicegame.ui.pages.FaultView
import com.example.practicegame.ui.pages.SecondView
import com.example.practicegame.ui.pages.StartPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
            val maxScore =  mainViewModel.exampleValue.collectAsState(initial = 0).value
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "first_screen"
            ) {
                composable("first_screen") {
                    StartPage(
                        onClick = {
                        navController.navigate("game_screen") {
                        }
                    },
                        score = maxScore)
                }
                composable("game_screen") {
                    SecondView(goToLoseScreen = {navController.navigate("fault_screen"){
                        popUpTo("first_screen") { inclusive = true }
                    }
                    mainViewModel.saveValue(maxOf(it,maxScore))})
                }
                composable("fault_screen"){
                    FaultView(openStartScreen ={navController.navigate("first_screen"){
                        popUpTo("first_screen") { inclusive = true }
                    } } )
                }
//            StartPage(onClick = {})
            }
        }
    }
}



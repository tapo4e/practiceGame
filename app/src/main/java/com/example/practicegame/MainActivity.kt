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
            val _timeLeft = mainViewModel.timeLeft.collectAsState().value
            NavHost(
                navController = navController,
                startDestination = "first_screen"
            ) {
                composable("first_screen") {
                    StartPage(
                        onClick = {
                        navController.navigate("game_screen") {
                        }
                            mainViewModel.startTimer()
                    },
                        score = maxScore)
                }
                composable("game_screen") {
                    SecondView(goToLoseScreen = {navController.navigate("fault_screen"){
                        popUpTo("first_screen") { inclusive = true }
                        mainViewModel.pauseTimer()
                        mainViewModel.resetTimer()
                    }
                    mainViewModel.saveValue(maxOf(it,maxScore))},
                        timer = mainViewModel.formatTime(mainViewModel.timeLeft.collectAsState().value),
                        endTimer = mainViewModel.isRunning.collectAsState().value,
                        goToStartScreen = {if(!mainViewModel.isRunning.value)navController.navigate("first_screen"){
                            popUpTo("first_screen") { inclusive = true }
                        } }
                        )
                }
                composable("fault_screen"){
                    FaultView(openStartScreen ={navController.navigate("first_screen"){
                        popUpTo("first_screen") { inclusive = true }
                    } } )
                }
            }
        }
    }
}



package com.example.weight_tracker

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weight_tracker.ui.views.AddWeight
import com.example.weight_tracker.ui.views.WeightList

@Composable
fun Navigation(navController: NavHostController, viewModel: WeightViewModel) {
    NavHost(navController, startDestination = "weightlist") {
        composable("weightlist") {
            WeightList(viewModel, navController)
        }
        composable("addweight") {
            AddWeight(viewModel)
        }
    }
}
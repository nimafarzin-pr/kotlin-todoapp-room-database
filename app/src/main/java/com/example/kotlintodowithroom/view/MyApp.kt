package com.example.kotlintodowithroom.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlintodowithroom.viewModel.NotesViewModel

@Composable
fun MyApp(viewModel: NotesViewModel) {
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()

    NavHost(navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(
                state = state,
                navController = navController,
                onEvent = viewModel::onEvent
            )


        }
        composable("AddNoteScreen") {
            AddNoteScreen(
                state = state,
                navController = navController,
                onEvent = viewModel::onEvent
            )


        }
    }
}
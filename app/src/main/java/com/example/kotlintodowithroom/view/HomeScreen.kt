package com.example.kotlintodowithroom.view

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kotlintodowithroom.R
import com.example.kotlintodowithroom.view.components.MyAppBar
import com.example.kotlintodowithroom.view.components.NoteItem
import com.example.kotlintodowithroom.viewModel.NoteEvents
import com.example.kotlintodowithroom.viewModel.NoteState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NoteEvents) -> Unit
) {


    Scaffold(
        topBar = {
            MyAppBar(
                title = R.string.home_screen_Title,
                endAction = {
                    onEvent(NoteEvents.SortNote)
                },
                endIcon = Icons.Rounded.Sort,
            )
        },
        floatingActionButton = {

            FloatingActionButton(onClick = {
                val bundle = Bundle()
                bundle.putBoolean("isUpdate", true)

                state.title.value = ""
                state.description.value = ""
                state.id.value = null
                navController.navigate("AddNoteScreen")
            }) {


                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add New Note")

            }
        }

    ) { padding ->

        if (state.notes.isEmpty()) {
            EmptyContentIcon()
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {


            items(state.notes.size) { index ->
                NoteItem(state = state, index = index, onEvent = onEvent,
                    onEdit = {
                        state.title.value = state.notes[index].title
                        state.description.value = state.notes[index].description
                        state.id.value = state.notes[index].id
                        navController.navigate("AddNoteScreen")
                    })
            }


        }

    }

}


@Composable
fun EmptyContentIcon() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            tint = Color.Gray,
            imageVector = Icons.Filled.Inbox,

            contentDescription = "Empty List"
        )
        
        Text(
            modifier = Modifier.padding(top = 120.dp),
            text = "You don't have any notes yet")
    }
}

package com.example.kotlintodowithroom.view
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kotlintodowithroom.R
import com.example.kotlintodowithroom.view.components.MyAppBar
import com.example.kotlintodowithroom.view.components.MyTextField
import com.example.kotlintodowithroom.viewModel.NoteEvents
import com.example.kotlintodowithroom.viewModel.NoteState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NoteEvents) -> Unit
) {
    var isTitleEmpty by remember {
        mutableStateOf(false)
    }

    fun isEmpty(value: String): Boolean {
        isTitleEmpty = value.isEmpty()
        return isTitleEmpty
    }


    Scaffold(
        topBar = {
            MyAppBar(
                title = if (state.id.value == null) R.string.home_add_note_Title else R.string.home_edit_note_Title,
                startAction = {
                    navController.popBackStack()
                },
                startIcon = Icons.Rounded.ArrowBack,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) { ->
            MyTextField(

                value = state.title.value,
                onValueChange = {
                    state.title.value = it
                    isEmpty(it)

                },

                placeHolder = "Title",
                validator = { value -> isTitleEmpty },
                errorMessage = "Please enter a title"
            )

            MyTextField(

                value = state.description.value,
                onValueChange = { state.description.value = it },

                placeHolder = "Description",
                validator = { false },
                errorMessage = ""
            )


            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
//                    border = BorderStroke(1.dp, Color.Blue),

                    onClick = {
                        isEmpty(state.title.value)
                        if (!isEmpty(state.title.value)) {
                            onEvent(
                                NoteEvents.SaveNote(
                                    title = state.title.value,
                                    description = state.description.value,
                                    id = state.id.value

                                )
                            )
                            navController.popBackStack()
                        }

                    }) {
                    Text(
                        text = if (state.id.value != null) "Update Note" else "Add Note",
                        style = TextStyle(color = Color.White)
                    )
                }
            }

        }
    }
}
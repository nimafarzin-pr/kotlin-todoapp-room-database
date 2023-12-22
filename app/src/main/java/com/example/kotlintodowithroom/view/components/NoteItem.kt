package com.example.kotlintodowithroom.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlintodowithroom.viewModel.NoteEvents
import com.example.kotlintodowithroom.viewModel.NoteState


@Composable
fun NoteItem(
    state: NoteState,
    index: Int,
    onEvent: (NoteEvents) -> Unit,
    onEdit: () -> Unit,

    ) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shadowElevation = 8.dp,// play with the elevation values

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp),


            ) {
            Column(
                modifier = Modifier.weight(1f)

            ) {
                Text(
                    text = state.notes[index].title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,

                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.notes[index].description,
                    fontSize = 16.sp,

                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            IconButton(onClick = {
                onEdit()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = "Delete Note",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            IconButton(onClick = {
                onEvent(
                    NoteEvents.DeleteNote(state.notes[index])
                )
            }) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete Note",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

        }
    }

}
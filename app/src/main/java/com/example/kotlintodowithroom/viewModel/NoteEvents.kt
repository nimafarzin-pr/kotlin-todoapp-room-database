package com.example.kotlintodowithroom.viewModel

import com.example.kotlintodowithroom.model.Note

sealed interface NoteEvents {

     object SortNote : NoteEvents

    data class DeleteNote(val note: Note) : NoteEvents

    data class SaveNote(
        val title: String,
        val description: String,
        val id:Int?
    ) : NoteEvents
}

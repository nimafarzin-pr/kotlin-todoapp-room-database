package com.example.kotlintodowithroom.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintodowithroom.model.Note
import com.example.kotlintodowithroom.model.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: NoteDao
) : ViewModel() {

    private val isSortedByDateAdded = MutableStateFlow(true)

    private var notes =
        isSortedByDateAdded.flatMapLatest { sort ->
            if (sort) {
                dao.getNoteOrderByDateAdded()
            } else {
                dao.getNotesOrderByTitle()
            }

        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(NoteState())
    val state =
        combine(_state, isSortedByDateAdded, notes) { state, isSortedByDateAdded, notes ->
            state.copy(
                notes = notes,


                )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(4000), NoteState())

    fun onEvent(event: NoteEvents) {
        when (event) {
            is NoteEvents.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }


            is NoteEvents.SaveNote -> {
                val note = Note(
                    title = state.value.title.value,
                    description = state.value.description.value,
                    id = state.value.id?.value,
                    dateAdded = System.currentTimeMillis(),
                )

                viewModelScope.launch {
                    dao.insertNote(note)
                }

                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        description = mutableStateOf("")
                    )
                }
            }

            NoteEvents.SortNote -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
        }
    }

}
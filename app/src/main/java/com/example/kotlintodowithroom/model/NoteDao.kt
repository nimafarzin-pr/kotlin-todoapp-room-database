package com.example.kotlintodowithroom.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // Upsert do add and update in one function
    // suspend make our function asynchronous
    @Upsert
    suspend fun insertNote(note:Note)


    @Delete
    suspend fun deleteNote(note:Note)


    @Query("SELECT * FROM note ORDER BY dateAdded")
    fun getNoteOrderByDateAdded():Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getNotesOrderByTitle():Flow<List<Note>>


}
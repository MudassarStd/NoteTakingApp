package com.example.notestakingapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notestakingapp.database.AppDatabase
import com.example.notestakingapp.database.Note

class NotesRepository(val appContext : Context) {

    val db = AppDatabase.getInstance(appContext)
    val dbDao = db.notesDao()

    val allNotes : LiveData<List<Note>> = dbDao.fetchAllNotes()


    suspend fun insertNote(note: Note) {
        dbDao.insertNote(note)
    }


    // IN CASE: dao returns list<Note>
//    suspend fun fetchAllNotes() : List<Note>
//    {
//        return dbDao.fetchAllNotes()
//    }


}
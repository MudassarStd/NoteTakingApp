package com.example.notestakingapp.datamodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notestakingapp.database.Note
import com.example.notestakingapp.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository = NotesRepository(application)

    val notes : LiveData<List<Note>> = repository.allNotes

//    private val _notes = MutableLiveData<List<Note>>()
//    val notes: LiveData<List<Note>> get() = _notes


    init {

    }

    suspend fun insertNote(note : Note)
    {
        repository.insertNote(note)
    }


    // IN CASE: dao returns list<Note>
//    fun fetchAllNotes()
//    {
//        viewModelScope.launch {
//            val notes = repository.fetchAllNotes()
//            _notes.postValue(notes)
//        }
//    }

}
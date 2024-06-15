package com.example.notestakingapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : Note)

    @Update
    suspend fun updateNote(note : Note)

    @Query("select * from Note order by noteId desc")
    fun fetchAllNotes() : LiveData<List<Note>>

//    @Query("update Note set noteTitle = :title")
//    suspend fun updateByValues(title : String, descrip : String, id : Int) : List<Note>


}
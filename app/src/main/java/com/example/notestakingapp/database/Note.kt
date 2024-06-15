package com.example.notestakingapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.format.DateTimeFormatter
import java.util.Date

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId : Int,
    val noteTitle : String,
    val noteDescription : String,
    val timeStamp : String
)

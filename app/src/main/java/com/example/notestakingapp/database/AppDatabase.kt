package com.example.notestakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notestakingapp.utilities.DATABASE_NAME

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun notesDao() : NotesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
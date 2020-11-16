package com.example.carrier.model.repos.localrepo

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ShiftStatusCodeDatabase : RoomDatabase() {

    abstract fun statusCodeDao() : ShiftStatusCodeDao
    // creating a synchronized version of the database in order to prevent seperate instances of it opening
    // also made it a singleton for obvious reasons.
    companion object{
        @Volatile
        private var INSTANCE: ShiftStatusCodeDatabase? = null
        fun getDatabase(context: Context) : ShiftStatusCodeDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ShiftStatusCodeDatabase::class.java,
                    "shiftStatusCode_Repo"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
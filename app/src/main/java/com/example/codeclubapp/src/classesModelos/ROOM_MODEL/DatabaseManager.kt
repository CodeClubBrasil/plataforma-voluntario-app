package com.example.codeclubapp.src.classesModelos.ROOM_MODEL

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserRoom::class], version = 2)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        private var INSTANCE: DatabaseManager? = null

        private fun createDatabaseInstance(context: Context): DatabaseManager {
            return synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseManager::class.java,
                    "database_code_club"
                ).fallbackToDestructiveMigration().build()
            }
        }
        fun getDatabaseInstance(context: Context): DatabaseManager {
            return INSTANCE ?: createDatabaseInstance(context).also {
                INSTANCE = it
            }
        }
    }
}


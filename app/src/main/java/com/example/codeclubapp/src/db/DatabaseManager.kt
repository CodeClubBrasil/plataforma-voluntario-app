package com.example.codeclubapp.src.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codeclubapp.src.classesModelos.user.UserCodeClub
import com.example.codeclubapp.src.db.user.UserCodeClubeDAO

@Database(entities = [UserCodeClub::class], version = 2)
abstract class DatabaseManager : RoomDatabase() {
    abstract fun userDAO(): UserCodeClubeDAO
}


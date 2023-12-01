package com.example.codeclubapp.src.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codeclubapp.src.db.entities.RoomUser
import com.example.codeclubapp.src.db.user.UserCodeClubeDAO

@Database(entities = [RoomUser::class], version = 8)
abstract class DatabaseManager : RoomDatabase() {
    abstract fun userDAO(): UserCodeClubeDAO
}


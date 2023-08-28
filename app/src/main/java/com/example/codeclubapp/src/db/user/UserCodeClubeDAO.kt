package com.example.codeclubapp.src.db.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.codeclubapp.src.classesModelos.user.UserCodeClub

@Dao
interface UserCodeClubeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewUser(userCodeClub: UserCodeClub)

}
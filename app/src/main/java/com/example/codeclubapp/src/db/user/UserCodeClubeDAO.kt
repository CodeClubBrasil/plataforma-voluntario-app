package com.example.codeclubapp.src.db.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codeclubapp.src.db.entities.RoomUser

@Dao
interface UserCodeClubeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewUser(roomUser: RoomUser)

    @Query("SELECT * FROM cc_tbl_user WHERE username = :username")
    fun getUserByUsername(username: String) : RoomUser

    @Query("DELETE FROM cc_tbl_user WHERE username = :username")
    fun deleteUserByNickname(username: String)
}
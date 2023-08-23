package com.example.codeclubapp.src.classesModelos.ROOM_MODEL

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    //metodos de conveniencia
    @Insert
    fun insertNewItem(userRoom: UserRoom){}

    @Update
    fun updateNewItem(userRoom: UserRoom)

    @Delete
    fun deleteItem(userRoom: UserRoom)

    //metodos de escrita -> a checar
    @Query("SELECT * FROM table_user")
    fun getAllUsers(): List<UserRoom>

}
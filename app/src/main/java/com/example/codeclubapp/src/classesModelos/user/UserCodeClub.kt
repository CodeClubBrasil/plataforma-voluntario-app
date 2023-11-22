package com.example.codeclubapp.src.classesModelos.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cc_tbl_user")
data class UserCodeClub(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String? = null,
    var lastName: String? = null,
    private var password: String? = null,
    @Embedded var address: Address?,
    @Embedded var contacts: Contacts?,
    @Embedded var roomDBAvaiableTime: RoomDBAvaiableTime?,
    @Embedded var knowledges: Knowledges?
){
    fun setPassword(password: String?){
        this.password = password
    }

    fun getPassword() : String?{
        return this.password
    }
}
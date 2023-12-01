package com.example.codeclubapp.src.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.codeclubapp.src.retrofit.dto.user.CreateUser
import com.example.codeclubapp.src.retrofit.user.UserAPI
import com.example.codeclubapp.src.utils.CCUtils

@Entity(tableName = "cc_tbl_user")
data class RoomUser(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    var active: Boolean,
    var city: String,
    var createdAt: String,
    var email: String,
    var knowledges: String,
    var lastname: String,
    var neighborhood: String,
    var password: String,
    var state: String,
    var telephone: String,
    var updatedat: String,
    var username: String,
    var name: String? = null,
    @Embedded var roomAvaiableTime: RoomAvaiableTime?,
    var needToBePersisted: Boolean,
    var logged: Boolean
) {
    fun toCreateUser(): UserAPI {
        val knowledgesList = CCUtils.stringToListOfString(this.knowledges)
        val contactList = CCUtils.stringToListOfString(this.telephone)

        val toAvailableTime = this.roomAvaiableTime!!.toAvailableTime()
        val listOfAvailableTime = listOf(toAvailableTime)

        return UserAPI(
            true, listOfAvailableTime, this.city, this.createdAt,
            this.email, knowledgesList, this.lastname, this.name!!, this.neighborhood,
            this.password, this.state, contactList, this.createdAt
        )
    }
}
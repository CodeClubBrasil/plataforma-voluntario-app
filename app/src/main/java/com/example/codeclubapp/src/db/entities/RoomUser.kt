package com.example.codeclubapp.src.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cc_tbl_user")
data class RoomUser(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
    @Embedded var roomAvaiableTime: RoomAvaiableTime?
)
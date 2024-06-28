package com.paulus.project_uas_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class User(
    @ColumnInfo(name="username")
    var username:String,
    @ColumnInfo(name="email")
    var email:String,
    @ColumnInfo(name="password")
    var password:String,
)
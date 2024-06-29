package com.paulus.project_uas_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name="username")
    var username:String,
    @ColumnInfo(name="email")
    var email:String,
    @ColumnInfo(name="password")
    var password:String,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}
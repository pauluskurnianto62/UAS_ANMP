package com.paulus.project_uas_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="author")
    var author:String,
    @ColumnInfo(name="short_desc")
    var short_desc:String,
    //@ColumnInfo(name="image")
    //var image:String,
    @ColumnInfo(name="picture")
    var picture:String,
    @ColumnInfo(name="article")
    var article:String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}
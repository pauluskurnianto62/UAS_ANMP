package com.paulus.project_uas_anmp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user:User)

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    fun selectUser(username:String, password:String): User

    @Query("UPDATE user SET firstname=:firstname, lastname=:lastname, password=:password WHERE username = :username")
    fun changeProfile(firstname:String, lastname:String, password:String, username:String)

}
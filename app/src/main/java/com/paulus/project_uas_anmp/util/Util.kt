package com.paulus.project_uas_anmp.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.paulus.project_uas_anmp.model.NewsDatabase
import com.paulus.project_uas_anmp.model.UserDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE news RENAME COLUMN image TO picture")
    }
}

val DB_NAME = "newsesdb"
val DB_NAME2 = "userdb"


fun buildUserDb(context: Context): UserDatabase {
    val db = UserDatabase.buildDatabase(context)
    return db
}

fun buildNewsDb(context: Context): NewsDatabase {
    val db = NewsDatabase.buildDatabase(context)
    return db
}
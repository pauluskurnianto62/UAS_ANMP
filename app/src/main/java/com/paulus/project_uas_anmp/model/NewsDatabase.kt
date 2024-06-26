package com.paulus.project_uas_anmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paulus.project_uas_anmp.util.DB_NEWS_NAME
import com.paulus.project_uas_anmp.util.MIGRATION_1_2

@Database(entities = arrayOf(News::class), version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile private var instance: NewsDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java, DB_NEWS_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
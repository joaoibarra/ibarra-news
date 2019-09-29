package com.ibarra.news.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibarra.news.data.db.dao.SourceDao
import com.ibarra.news.data.db.entity.Source

@Database(entities = arrayOf(Source::class), version = AppDatabase.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSourceDao(): SourceDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "ibarra_news.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .build()
    }
}
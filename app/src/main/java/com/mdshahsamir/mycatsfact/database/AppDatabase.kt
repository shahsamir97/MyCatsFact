package com.mdshahsamir.mycatsfact.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mdshahsamir.mycatsfact.model.Cat

@Database(entities = [Cat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun catDao() : CatDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "animal_database"

        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
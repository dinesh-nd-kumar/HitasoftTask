package com.example.hitasofttask.PostRepo.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hitasofttask.model.User

object DatabaseBuilder {
    @Database(entities = [User::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun postDao(): PostDao

    }

    private var INSTANCE: AppDatabase? = null
    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "productDB"
        ).build()
}
package ru.cft.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AccountItem::class], version = 1, exportSchema = false)
abstract class AuthRoomDatabase : RoomDatabase() {

    abstract fun getAccount(): RoomDao

    companion object {
        private var database: AuthRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AuthRoomDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, AuthRoomDatabase::class.java, "db")
                        .build()
                database as AuthRoomDatabase
            } else {
                database as AuthRoomDatabase
            }
        }
    }
}
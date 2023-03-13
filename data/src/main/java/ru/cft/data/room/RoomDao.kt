package ru.cft.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(accountItem : AccountItem)

    @Query("SELECT * FROM auth_table WHERE email=:email")
    fun searchEmail(email: String):AccountItem

    @Query("SELECT * FROM auth_table WHERE password=:password")
    fun searchAccount(password:String):AccountItem
}
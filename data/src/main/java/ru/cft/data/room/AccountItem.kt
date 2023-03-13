package ru.cft.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_table")
data class AccountItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    val firstName: String,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    val lastName: String,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    val email: String,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    val password: String,
)

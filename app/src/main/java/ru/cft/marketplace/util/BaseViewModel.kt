package ru.cft.marketplace.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.cft.data.room.AuthRoomDatabase
import ru.cft.data.room.RoomRepositoryImpl

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    private val dao = AuthRoomDatabase.getInstance(context).getAccount()
    val repository = RoomRepositoryImpl(dao)
}
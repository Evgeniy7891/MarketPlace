package ru.cft.marketplace.screens.authorization

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.cft.domain.usecases.SearchAccountUseCase
import ru.cft.marketplace.util.BaseViewModel

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private val accountUseCase = SearchAccountUseCase(repository)

    val result = MutableLiveData<Boolean>()

    fun searchAccount(name: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val account = accountUseCase.invoke(name, password)
            delay(1000)
            result.postValue(account)
        } catch (e: java.lang.NullPointerException) {
            result.postValue(false)
            println("Error ${e.message.toString()}")
        }
    }
}
package ru.cft.marketplace.screens.authorization

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.cft.domain.models.AccountModel
import ru.cft.domain.usecases.InsertUseCase
import ru.cft.domain.usecases.SearchEmailUseCase
import ru.cft.marketplace.PROCCESING
import ru.cft.marketplace.util.BaseViewModel

class SignInViewModel(application: Application) : BaseViewModel(application) {

    val insertUseCase = InsertUseCase(repository)
    val emailUseCase = SearchEmailUseCase(repository)

    val result = MutableLiveData<Boolean>()

    fun insert(accountModel: AccountModel) =
        viewModelScope.launch(Dispatchers.IO) {
            insertUseCase.invoke(accountModel)
        }

    fun searchEmail(email: String) = viewModelScope.launch(Dispatchers.IO) {
        val answer = emailUseCase.invoke(email)
        delay(PROCCESING)
        result.postValue(answer)
    }
}
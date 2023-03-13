package ru.cft.marketplace.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.data.providers.ProvidersRepositoryImpl
import ru.cft.domain.models.LatestModel
import ru.cft.domain.models.NetworkState
import ru.cft.domain.models.Sale
import ru.cft.domain.usecases.GetLatestUseCase
import ru.cft.domain.usecases.GetSaleUseCase

class HomeViewModel : ViewModel() {

    private val repository = ProvidersRepositoryImpl()
    private val latestUseCase = GetLatestUseCase(repository)
    private val saleUseCase = GetSaleUseCase(repository)

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _result = MutableStateFlow<LatestModel?>(null)
    val result = _result.asStateFlow()

    fun getLatest() = viewModelScope.launch {
        when (val response = latestUseCase.invoke()) {
            is NetworkState.Error -> _errorMessage.emit(response.throwable)
            is NetworkState.Loading -> isLoading.value
            is NetworkState.Success -> _result.emit(response.success)
        }
    }

    private val _errorMessageSale = MutableSharedFlow<String>()
    val errorMessageSale = _errorMessageSale.asSharedFlow()

    private val _isLoadingSale = MutableStateFlow<Boolean>(false)
    val isLoadingSale = _isLoadingSale.asStateFlow()

    private val _resultSale = MutableStateFlow<Sale?>(null)
    val resultSale = _resultSale.asStateFlow()

    fun getSale() = viewModelScope.launch {
        when (val response = saleUseCase.invoke()) {
            is NetworkState.Error -> _errorMessageSale.emit(response.throwable)
            is NetworkState.Loading -> isLoadingSale.value
            is NetworkState.Success -> _resultSale.emit(response.success)
        }
    }
}
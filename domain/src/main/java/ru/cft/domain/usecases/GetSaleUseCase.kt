package ru.cft.domain.usecases

import ru.cft.domain.models.NetworkState
import ru.cft.domain.models.Sale
import ru.cft.domain.repository.ProvidersRepository

class GetSaleUseCase ( private val providersRepository: ProvidersRepository) {

    suspend operator fun invoke() : NetworkState<Sale> {
        return providersRepository.getSale()
    }
}
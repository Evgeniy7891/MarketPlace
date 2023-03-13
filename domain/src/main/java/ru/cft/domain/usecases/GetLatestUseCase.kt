package ru.cft.domain.usecases

import ru.cft.domain.models.LatestModel
import ru.cft.domain.models.NetworkState
import ru.cft.domain.repository.ProvidersRepository

class GetLatestUseCase( private val providersRepository: ProvidersRepository) {

    suspend operator fun invoke() : NetworkState<LatestModel> {
        return providersRepository.getLatest()
    }
}
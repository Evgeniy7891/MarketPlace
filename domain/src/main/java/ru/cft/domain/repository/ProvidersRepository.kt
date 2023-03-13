package ru.cft.domain.repository

import ru.cft.domain.models.LatestModel
import ru.cft.domain.models.NetworkState
import ru.cft.domain.models.Sale

interface ProvidersRepository {
    suspend fun getLatest():NetworkState<LatestModel>
    suspend fun getSale():NetworkState<Sale>
}
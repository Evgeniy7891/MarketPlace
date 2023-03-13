package ru.cft.data.providers

import kotlinx.coroutines.Dispatchers
import ru.cft.data.util.safeApiCall
import ru.cft.domain.models.LatestModel
import ru.cft.domain.models.NetworkState
import ru.cft.domain.models.Sale
import ru.cft.domain.repository.ProvidersRepository

class ProvidersRepositoryImpl : ProvidersRepository {

    private val remoteDataSource = ProductRemoteDataSourceImpl()

    override suspend fun getLatest(): NetworkState<LatestModel> {
        return safeApiCall(Dispatchers.IO) {
            remoteDataSource.getLatest()
        }
    }

    override suspend fun getSale(): NetworkState<Sale> {
        return safeApiCall(Dispatchers.IO) {
            remoteDataSource.getSale()
        }
    }
}
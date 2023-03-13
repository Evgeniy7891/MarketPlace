package ru.cft.data.providers

import ru.cft.domain.models.LatestModel
import ru.cft.domain.models.Sale

class ProductRemoteDataSourceImpl() {

    suspend fun getLatest(): LatestModel {
        return Retrofit.api.getLatest()
    }
    suspend fun getSale(): Sale {
        return Retrofit.api.getSale()
    }
}
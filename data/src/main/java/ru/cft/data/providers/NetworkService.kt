package ru.cft.data.providers

import retrofit2.http.GET
import ru.cft.domain.models.LatestModel
import ru.cft.domain.models.Sale

interface NetworkService {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest() : LatestModel

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getSale() : Sale
}
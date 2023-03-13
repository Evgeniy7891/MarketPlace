package ru.cft.domain.repository

import ru.cft.domain.models.AccountModel

interface RoomRepository {
    suspend fun insertAccount(accountModel: AccountModel)
    suspend fun searchEmail(email: String) : Boolean
    suspend fun searchAccount(name: String, password: String) : Boolean
}
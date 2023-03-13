package ru.cft.data.room

import ru.cft.domain.models.AccountModel
import ru.cft.domain.repository.RoomRepository

class RoomRepositoryImpl(private val roomDao: RoomDao) : RoomRepository {

    override suspend fun insertAccount(accountModel: AccountModel) {
        val account: AccountItem = AccountItem(
            id = accountModel.id,
            firstName = accountModel.firstName,
            lastName = accountModel.lastName,
            email = accountModel.email,
            password = accountModel.password+accountModel.firstName
        )
        roomDao.insert(account)
    }

    override suspend fun searchEmail(email: String): Boolean {
        val result = roomDao.searchEmail(email)
        return if (result != null) true else false
    }

    override suspend fun searchAccount(name: String, password: String): Boolean {
        val result = roomDao.searchAccount(password+name)
        return  if (name != result.firstName) false else true
    }
}
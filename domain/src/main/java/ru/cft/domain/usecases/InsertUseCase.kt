package ru.cft.domain.usecases

import ru.cft.domain.models.AccountModel
import ru.cft.domain.repository.RoomRepository


class InsertUseCase (private val roomRepository: RoomRepository) {

    suspend operator fun invoke(accountModel: AccountModel) {
        return roomRepository.insertAccount(accountModel)
    }
}
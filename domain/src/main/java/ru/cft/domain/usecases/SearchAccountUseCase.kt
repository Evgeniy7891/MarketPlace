package ru.cft.domain.usecases

import ru.cft.domain.repository.RoomRepository

class SearchAccountUseCase(private val roomRepository: RoomRepository) {

    suspend operator fun invoke(name: String, password: String) : Boolean {
        return  roomRepository.searchAccount(name, password)
    }
}
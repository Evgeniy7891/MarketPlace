package ru.cft.domain.usecases

import ru.cft.domain.repository.RoomRepository

class SearchEmailUseCase (private val roomRepository: RoomRepository) {

    suspend operator fun invoke(email: String) : Boolean {
        return roomRepository.searchEmail(email)
    }
}
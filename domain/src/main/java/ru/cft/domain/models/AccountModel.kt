package ru.cft.domain.models

data class AccountModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)

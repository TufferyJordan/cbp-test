package com.cbp.test.features.input.domain

interface InputInteractor {
    fun input(
        identifier: String,
        title: String,
        expirationDate: Long
    )
}

data class InputData(
    val identifier: String,
    val title: String,
    val expirationDate: Long
)

interface InputRepository {
    suspend fun saveInput(
        data: InputData
    )
}

class InputRepositoryException: Exception("An error occurred while inputting the data, please try again.")

interface InputPresenter {
    fun presentSuccess()
    fun presentError(exception: InputRepositoryException)
}
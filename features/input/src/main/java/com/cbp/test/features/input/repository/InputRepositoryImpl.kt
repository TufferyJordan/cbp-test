package com.cbp.test.features.input.repository

import com.cbp.test.features.input.domain.InputData
import com.cbp.test.features.input.domain.InputRepository
import com.cbp.test.interfaces.data.dao.ProductService
import com.cbp.test.interfaces.data.entities.ProductEntity

class InputRepositoryImpl(
    private val service: ProductService
) : InputRepository {
    override suspend fun saveInput(data: InputData) {
        service.insert(
            ProductEntity(
                identifier = data.identifier,
                productTitle = data.title,
                expirationDate = data.expirationDate
            )
        )
    }
}
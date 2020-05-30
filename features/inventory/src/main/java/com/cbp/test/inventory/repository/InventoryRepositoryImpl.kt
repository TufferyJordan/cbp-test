package com.cbp.test.inventory.repository

import com.cbp.test.interfaces.data.dao.ProductService
import com.cbp.test.inventory.interactor.InventoryRepository
import com.cbp.test.inventory.interactor.ProductData
import com.cbp.test.inventory.interactor.ProductListData

class InventoryRepositoryImpl(
    private val service: ProductService
): InventoryRepository {
    override suspend fun getAllProducts(): ProductListData? {
        return try {
            service.getAll()?.let { list ->
                ProductListData(
                    list.map {
                        ProductData(
                            identifier = it.identifier,
                            title = it.productTitle,
                            expirationDate = it.expirationDate
                        )
                    }
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}
package com.cbp.test.data.dao

import com.cbp.test.data.entities.ProductEntityRoom
import com.cbp.test.interfaces.data.dao.ProductService
import com.cbp.test.interfaces.data.entities.ProductEntity

class ProductServiceRoom(
    private val daoRoom: ProductDaoRoom
) : ProductService {
    override suspend fun insert(t: ProductEntity) {
        daoRoom.insert(t.toRoom())
    }

    override suspend fun update(t: ProductEntity) {
        daoRoom.update(t.toRoom())
    }

    override suspend fun delete(t: ProductEntity) {
        daoRoom.delete(t.toRoom())
    }

    override suspend fun getAll(): List<ProductEntity>? = daoRoom.getAll()?.map { it.toEntity() }

    private fun ProductEntityRoom.toEntity() = ProductEntity(
        identifier,
        productTitle,
        expirationDate
    )

    private fun ProductEntity.toRoom() = ProductEntityRoom(
        identifier = identifier,
        productTitle = productTitle,
        expirationDate = expirationDate
    )
}
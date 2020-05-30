package com.cbp.test.interfaces.data.dao

import com.cbp.test.interfaces.data.entities.ProductEntity

interface ProductService {
    suspend fun insert(t: ProductEntity)
    suspend fun update(t: ProductEntity)
    suspend fun delete(t: ProductEntity)
    suspend fun getAll(): List<ProductEntity>?
}
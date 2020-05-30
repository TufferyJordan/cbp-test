package com.cbp.test.interfaces.data.dao

import com.cbp.test.interfaces.data.entities.ProductEntity

interface ProductDao<T> :
    BaseDao<T> {
    suspend fun getAll(): List<@JvmSuppressWildcards T>?
}
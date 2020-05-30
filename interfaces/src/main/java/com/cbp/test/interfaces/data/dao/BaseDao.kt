package com.cbp.test.interfaces.data.dao

interface BaseDao<T> {
    suspend fun insert(t: T)
    suspend fun update(t: T)
    suspend fun delete(t: T)
}
package com.cbp.test.data.dao

import androidx.room.*
import com.cbp.test.data.entities.ProductEntityRoom
import com.cbp.test.interfaces.data.dao.ProductDao

@Dao
interface ProductDaoRoom:
    ProductDao<ProductEntityRoom> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(t: ProductEntityRoom)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(t: ProductEntityRoom)

    @Delete
    override suspend fun delete(t: ProductEntityRoom)

    @Transaction
    @Query("SELECT * FROM product")
    override suspend fun getAll(): List<ProductEntityRoom>?
}
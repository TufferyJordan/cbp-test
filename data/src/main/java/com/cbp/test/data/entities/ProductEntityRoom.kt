package com.cbp.test.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cbp.test.interfaces.data.entities.ProductEntity


@Entity(tableName = "product")
class ProductEntityRoom(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val identifier: String,
    val productTitle: String,
    val expirationDate: Long
)
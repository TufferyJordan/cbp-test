package com.cbp.test.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cbp.test.data.dao.ProductDaoRoom
import com.cbp.test.data.entities.ProductEntityRoom

@Database(entities = [(ProductEntityRoom::class)],
    version = 1)
abstract class CBPDatabase  : RoomDatabase() {
    abstract fun productDao(): ProductDaoRoom
}

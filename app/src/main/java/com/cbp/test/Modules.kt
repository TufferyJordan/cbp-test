package com.cbp.test

import androidx.room.RoomDatabase
import com.cbp.test.data.CBPDatabase
import com.cbp.test.data.dao.ProductServiceRoom
import com.cbp.test.interfaces.data.dao.ProductDao
import com.cbp.test.interfaces.data.dao.ProductService
import com.cbp.test.interfaces.data.entities.ProductEntity
import com.cbp.test.interfaces.routing.BarcodeRouter
import com.cbp.test.interfaces.routing.InputRouter
import com.cbp.test.routing.BarcodeRouterImpl
import com.cbp.test.routing.InputRouterImpl
import org.koin.dsl.module

object Modules {
    val router = module {
        single<BarcodeRouter> { BarcodeRouterImpl(get()) }
        single<InputRouter> { InputRouterImpl(get()) }
    }

    fun database(db: CBPDatabase) = module {
        single { db }
        single<ProductService> { ProductServiceRoom(db.productDao()) }
    }
}
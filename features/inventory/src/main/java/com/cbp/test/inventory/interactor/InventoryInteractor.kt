package com.cbp.test.inventory.interactor



data class Product(
    val title: String,
    val expirationDate: Long
)

data class ProductList(
    val list: List<Product>
)

interface InventoryInteractor {
    fun load()
}

class InventoryException: Exception("An error occurred while requesting the data")


interface InventoryPresenter {
    fun presentError(exception: InventoryException)
    fun presentData(data: ProductList)
}

data class ProductData(
    val identifier: String,
    val title: String,
    val expirationDate: Long
)

data class ProductListData(
    val list: List<ProductData>
)
interface InventoryRepository {
    suspend fun getAllProducts(): ProductListData?
}
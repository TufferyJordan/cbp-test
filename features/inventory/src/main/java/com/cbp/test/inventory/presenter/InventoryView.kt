package com.cbp.test.inventory.presenter

interface InventoryView {
    fun displayError(error: String)
    fun displayData(viewModel: ProductListViewModel)
}

data class ProductViewModel(
    val title: String,
    val expirationDate: String
)

data class ProductListViewModel(
    val list: List<ProductViewModel>
)
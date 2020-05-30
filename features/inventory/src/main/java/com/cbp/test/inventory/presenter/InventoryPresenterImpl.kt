package com.cbp.test.inventory.presenter

import com.cbp.test.inventory.interactor.InventoryException
import com.cbp.test.inventory.interactor.InventoryPresenter
import com.cbp.test.inventory.interactor.ProductList

class InventoryPresenterImpl(
    private val view: InventoryView
) : InventoryPresenter {
    override fun presentError(exception: InventoryException) {
        view.displayError(exception.message ?: "")
    }

    override fun presentData(data: ProductList) {
        view.displayData(
            ProductListViewModel(
                data.list.map {
                    ProductViewModel(
                        it.title,
                        it.expirationDate.toString()
                    )
                }
            )
        )
    }
}
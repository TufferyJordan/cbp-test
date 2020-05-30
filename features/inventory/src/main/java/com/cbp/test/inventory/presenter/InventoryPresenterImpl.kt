package com.cbp.test.inventory.presenter

import com.cbp.test.inventory.interactor.InventoryException
import com.cbp.test.inventory.interactor.InventoryPresenter
import com.cbp.test.inventory.interactor.ProductList
import java.text.SimpleDateFormat
import java.util.*

class InventoryPresenterImpl(
    private val view: InventoryView
) : InventoryPresenter {
    override fun presentError(exception: InventoryException) {
        view.displayError(exception.message ?: "")
    }

    override fun presentData(data: ProductList) {
        val calendar = Calendar.getInstance()
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy")

        view.displayData(
            ProductListViewModel(
                data.list.map {
                    calendar.timeInMillis = it.expirationDate
                    ProductViewModel(
                        it.title,
                        dateFormatter.format(calendar.time)
                    )
                }
            )
        )
    }
}
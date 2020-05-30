package com.cbp.test.inventory

import com.cbp.test.interfaces.data.dao.ProductService
import com.cbp.test.inventory.interactor.InventoryInteractor
import com.cbp.test.inventory.interactor.InventoryInteractorImpl
import com.cbp.test.inventory.presenter.InventoryPresenterImpl
import com.cbp.test.inventory.presenter.InventoryView
import com.cbp.test.inventory.repository.InventoryRepositoryImpl
import kotlinx.coroutines.CoroutineScope

class InventoryDependencies(
    view: InventoryView,
    service: ProductService,
    lifecycleScope: CoroutineScope
) {
    val interactor: InventoryInteractor

    init {
        val presenter = InventoryPresenterImpl(view)
        val repository = InventoryRepositoryImpl(service)
        interactor = InventoryInteractorImpl(presenter, repository, lifecycleScope)
    }
}
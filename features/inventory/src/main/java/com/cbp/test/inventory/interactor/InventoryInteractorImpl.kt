package com.cbp.test.inventory.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class InventoryInteractorImpl(
    private val presenter: InventoryPresenter,
    private val repository: InventoryRepository,
    private val lifecycleScope: CoroutineScope
) : InventoryInteractor {

    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    override fun load() {
        ioScope.launch {
            repository.getAllProducts()?.let {
                lifecycleScope.launch {
                    presenter.presentData(
                        ProductList(
                            it.list.map {
                                Product(
                                    it.title,
                                    it.expirationDate
                                )
                            }
                        )
                    )
                }
            } ?: run {
                lifecycleScope.launch {
                    presenter.presentError(InventoryException())
                }
            }
        }
    }
}
package com.cbp.test.features.input.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class InputInteractorImpl(
    private val presenter: InputPresenter,
    private val repository: InputRepository,
    private val lifecycleScope: CoroutineScope
) : InputInteractor {

    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    override fun input(identifier: String, title: String, expirationDate: Long) {
        ioScope.launch {
            try {
                repository.saveInput(
                    InputData(
                        identifier,
                        title,
                        expirationDate
                    )
                )
                lifecycleScope.launch {
                    presenter.presentSuccess()
                }
            } catch (e: Exception) {
                lifecycleScope.launch {
                    presenter.presentError(InputRepositoryException())
                }
            }
        }
    }

}
package com.cbp.test.features.input

import com.cbp.test.features.input.domain.InputInteractor
import com.cbp.test.features.input.domain.InputInteractorImpl
import com.cbp.test.features.input.presenter.InputPresenterImpl
import com.cbp.test.features.input.presenter.InputView
import com.cbp.test.features.input.repository.InputRepositoryImpl
import com.cbp.test.interfaces.data.dao.ProductService
import kotlinx.coroutines.CoroutineScope

class InputDependencies(
    view: InputView,
    service: ProductService,
    lifecycleScope: CoroutineScope
) {
    val interactor: InputInteractor

    init {
        val presenter = InputPresenterImpl(view)
        val repository = InputRepositoryImpl(service)
        interactor = InputInteractorImpl(presenter, repository, lifecycleScope)
    }
}
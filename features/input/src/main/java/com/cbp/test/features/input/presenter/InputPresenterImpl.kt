package com.cbp.test.features.input.presenter

import com.cbp.test.features.input.domain.InputPresenter
import com.cbp.test.features.input.domain.InputRepositoryException

class InputPresenterImpl(
    private val view: InputView
) : InputPresenter {
    override fun presentSuccess() {
        view.displaySuccess()
    }

    override fun presentError(exception: InputRepositoryException) {
        view.displayError(exception.message ?: "")
    }

}
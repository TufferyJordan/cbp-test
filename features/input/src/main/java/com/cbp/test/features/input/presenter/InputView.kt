package com.cbp.test.features.input.presenter

interface InputView {
    fun displaySuccess()
    fun displayError(error: String)
}
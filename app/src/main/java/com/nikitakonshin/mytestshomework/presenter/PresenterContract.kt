package com.nikitakonshin.mytestshomework.presenter

import com.nikitakonshin.mytestshomework.view.ViewContract

internal interface PresenterContract {
    fun onAttach(viewContract: ViewContract)
    fun onDetach()
}

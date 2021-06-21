package com.nikitakonshin.mytestshomework.presenter.details

import com.nikitakonshin.mytestshomework.presenter.PresenterContract

internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}

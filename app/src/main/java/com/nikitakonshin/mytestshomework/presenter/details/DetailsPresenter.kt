package com.nikitakonshin.mytestshomework.presenter.details

import com.nikitakonshin.mytestshomework.view.ViewContract
import com.nikitakonshin.mytestshomework.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
//    private val viewContract: ViewDetailsContract?,
    private var count: Int = 0
) : PresenterDetailsContract {
    internal var _viewContract: ViewDetailsContract? = null

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        _viewContract?.setCount(count)
    }

    override fun onDecrement() {
        count--
        _viewContract?.setCount(count)
    }

    override fun onAttach(viewContract: ViewContract) {
        _viewContract = viewContract as ViewDetailsContract
    }

    override fun onDetach() {
        _viewContract = null
        _viewContract
    }
}

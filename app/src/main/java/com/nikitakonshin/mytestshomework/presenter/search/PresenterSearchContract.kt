package com.nikitakonshin.mytestshomework.presenter.search

import com.nikitakonshin.mytestshomework.presenter.PresenterContract

internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
    //onAttach
    //onDetach
}

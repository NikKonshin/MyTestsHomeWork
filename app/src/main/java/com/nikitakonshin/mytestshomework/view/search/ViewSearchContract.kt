package com.nikitakonshin.mytestshomework.view.search

import com.nikitakonshin.mytestshomework.view.ViewContract
import com.nikitakonshin.mytestshomework.model.SearchResult

internal interface ViewSearchContract : ViewContract {
    fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    )

    fun displayError()
    fun displayError(error: String)
    fun displayLoading(show: Boolean)
}

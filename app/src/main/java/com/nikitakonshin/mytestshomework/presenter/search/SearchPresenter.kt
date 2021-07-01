package com.nikitakonshin.mytestshomework.presenter.search

import com.nikitakonshin.mytestshomework.model.SearchResponse
import com.nikitakonshin.mytestshomework.repository.GitHubRepository
import com.nikitakonshin.mytestshomework.repository.RepositoryCallback
import com.nikitakonshin.mytestshomework.view.ViewContract
import com.nikitakonshin.mytestshomework.view.search.ViewSearchContract
import retrofit2.Response

/**
 * В архитектуре MVP все запросы на получение данных адресуются в Репозиторий.
 * Запросы могут проходить через Interactor или UseCase, использовать источники
 * данных (DataSource), но суть от этого не меняется.
 * Непосредственно Презентер отвечает за управление потоками запросов и ответов,
 * выступая в роли регулировщика движения на перекрестке.
 */

internal class SearchPresenter internal constructor(
    private val repository: GitHubRepository
) : PresenterSearchContract, RepositoryCallback {
    internal var _viewContract: ViewSearchContract? = null

    override fun searchGitHub(searchQuery: String) {
        _viewContract?.displayLoading(true)
        repository.searchGithub(searchQuery, this)
    }

    override fun onAttach(viewContract: ViewContract) {
        _viewContract = viewContract as ViewSearchContract
    }

    override fun handleGitHubResponse(response: Response<SearchResponse?>?) {
        _viewContract?.displayLoading(false)
        if (response != null && response.isSuccessful) {
            val searchResponse = response.body()
            val searchResults = searchResponse?.searchResults
            val totalCount = searchResponse?.totalCount
            if (searchResults != null && totalCount != null) {
                _viewContract?.displaySearchResults(
                    searchResults,
                    totalCount
                )
            } else {
                _viewContract?.displayError("Search results or total count are null")
            }
        } else {
            _viewContract?.displayError("Response is null or unsuccessful")
        }
    }

    override fun handleGitHubError() {
        _viewContract?.displayLoading(false)
        _viewContract?.displayError()
    }

    override fun onDetach() {
        _viewContract = null
    }
}

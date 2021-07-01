package com.nikitakonshin.mytestshomework.presenter.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nikitakonshin.mytestshomework.model.ScreenState
import com.nikitakonshin.mytestshomework.presenter.RepositoryContract
import com.nikitakonshin.mytestshomework.repository.GitHubApi
import com.nikitakonshin.mytestshomework.repository.GitHubRepository
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com"

class SearchViewModelCour(
    private val repository: RepositoryContract = GitHubRepository(
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubApi::class.java)
    )
) : ViewModel() {
    private val _liveData = MutableLiveData<ScreenState>()
    fun subscribeToLiveData() = _liveData
    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable -> handleError(throwable) }
    )

    private fun handleError(throwable: Throwable) {
        _liveData.value =
            ScreenState.Error(
                Throwable(
                    throwable.message ?: "Response is null or unsuccessful"
                )
            )
    }

    fun searchGitHub(searchQuery: String){
        _liveData.value = ScreenState.Loading
        viewModelCoroutineScope.launch {
            val searchResponse = repository.searchGithubAsync(searchQuery)
            val searchResults = searchResponse.searchResults
            val totalCount = searchResponse.totalCount
            if (searchResults != null && totalCount != null) {
                _liveData.value = ScreenState.Working(searchResponse)
            } else {
                _liveData.value =
                    ScreenState.Error(Throwable("Search results or total count are null"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}
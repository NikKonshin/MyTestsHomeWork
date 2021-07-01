package com.nikitakonshin.mytestshomework.presenter.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitakonshin.mytestshomework.model.ScreenState
import com.nikitakonshin.mytestshomework.model.SearchResponse
import com.nikitakonshin.mytestshomework.presenter.RepositoryContract
import com.nikitakonshin.mytestshomework.presenter.SchedulerProvider
import com.nikitakonshin.mytestshomework.repository.GitHubApi
import com.nikitakonshin.mytestshomework.repository.GitHubRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com"

class SearchViewModel(
    private val repository: RepositoryContract = GitHubRepository(
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubApi::class.java)
    ),
    private val appSchedulerProvider: SchedulerProvider = SearchSchedulerProvider()
) : ViewModel() {

    private val _liveData = MutableLiveData<ScreenState>()
    fun subscribeToLiveData(): LiveData<ScreenState> = _liveData

    fun searchGitHub(searchQuery: String) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            repository.searchGithub(searchQuery)
                .subscribeOn(appSchedulerProvider.io())
                .observeOn(appSchedulerProvider.ui())
                .doOnSubscribe { _liveData.value = ScreenState.Loading }
                .subscribeWith(object : DisposableObserver<SearchResponse>() {
                    override fun onNext(t: SearchResponse) {
                        val searchResults = t.searchResults
                        val totalCount = t.totalCount
                        if (searchResults != null && totalCount != null) {
                            _liveData.value = ScreenState.Working(t)
                        } else {
                            _liveData.value =
                                ScreenState.Error(Throwable("Search results or total count are null"))
                        }
                    }

                    override fun onError(e: Throwable) {
                        _liveData.value =
                            ScreenState.Error(
                                Throwable(
                                    e.message ?: "Response is null or unsuccessful"
                                )
                            )
                    }

                    override fun onComplete() {}

                })
        )
    }
}
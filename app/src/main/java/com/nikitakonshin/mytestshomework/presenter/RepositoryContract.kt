package com.nikitakonshin.mytestshomework.presenter

import com.nikitakonshin.mytestshomework.model.SearchResponse
import com.nikitakonshin.mytestshomework.repository.RepositoryCallback
import io.reactivex.Observable

interface RepositoryContract {

    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )

    fun searchGithub(
        query: String,
    ): Observable<SearchResponse>

    suspend fun searchGithubAsync(
        query: String
    ): SearchResponse
}
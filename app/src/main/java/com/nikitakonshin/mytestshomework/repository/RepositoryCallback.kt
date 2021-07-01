package com.nikitakonshin.mytestshomework.repository

import com.nikitakonshin.mytestshomework.model.SearchResponse
import retrofit2.Response

interface RepositoryCallback {
    fun handleGitHubResponse(response: Response<SearchResponse?>?)
    fun handleGitHubError()
}
package com.nikitakonshin.mytestshomework.repository
import com.nikitakonshin.mytestshomework.model.SearchResponse
import com.nikitakonshin.mytestshomework.model.SearchResult
import com.nikitakonshin.mytestshomework.presenter.RepositoryContract
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

internal class GitHubRepository(private val gitHubApi: GitHubApi): RepositoryContract {

   override  fun searchGithub(
        query: String,
        callback: RepositoryCallback
    ) {
        callback.handleGitHubResponse(Response.success(SearchResponse(42, listOf())))
    }

    override fun searchGithub(query: String): Observable<SearchResponse> {
        return Observable.just(generateSearchResponse())
    }

    override suspend fun searchGithubAsync(query: String): SearchResponse =
         generateSearchResponse()

    private fun generateSearchResponse(): SearchResponse {
        val list: MutableList<SearchResult> = mutableListOf()
        for (index in 1..100) {
            list.add(
                SearchResult(
                    id = index,
                    name = "Name: $index",
                    fullName = "FullName: $index",
                    private = Random.nextBoolean(),
                    description = "Description: $index",
                    updatedAt = "Updated: $index",
                    size = index,
                    stargazersCount = Random.nextInt(100),
                    language = "",
                    hasWiki = Random.nextBoolean(),
                    archived = Random.nextBoolean(),
                    score = index.toDouble()
                )
            )
        }
        return SearchResponse(list.size, list)
    }

}

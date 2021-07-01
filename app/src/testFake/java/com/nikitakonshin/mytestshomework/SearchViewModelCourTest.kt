package com.nikitakonshin.mytestshomework

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nikitakonshin.mytestshomework.model.ScreenState
import com.nikitakonshin.mytestshomework.model.SearchResponse
import com.nikitakonshin.mytestshomework.presenter.search.SearchViewModelCour
import com.nikitakonshin.mytestshomework.repository.GitHubRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@ExperimentalCoroutinesApi
class SearchViewModelCourTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var searchViewModel: SearchViewModelCour

    @Mock
    private lateinit var repository: GitHubRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        searchViewModel = SearchViewModelCour(repository)
    }

    @Test
    fun coroutines_TestReturnValueIsNotNull() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<ScreenState> {}
            val liveData = searchViewModel.subscribeToLiveData()

            Mockito.`when`(repository.searchGithubAsync(SEARCH_QUERY)).thenReturn(
                SearchResponse(1, listOf())
            )

            try {
                liveData.observeForever(observer)
                searchViewModel.searchGitHub(SEARCH_QUERY)
                Assert.assertNotNull(liveData.value)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun coroutines_TestReturnValueIsError() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<ScreenState> {}
            val liveData = searchViewModel.subscribeToLiveData()

            Mockito.`when`(repository.searchGithubAsync(SEARCH_QUERY)).thenReturn(
                SearchResponse(null, listOf())
            )

            try {
                liveData.observeForever(observer)
                searchViewModel.searchGitHub(SEARCH_QUERY)

                val value: ScreenState.Error = liveData.value as ScreenState.Error
                Assert.assertEquals(value.error.message, ERROR_TEXT)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun coroutines_TestReturnValueIsListNullError() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<ScreenState> {}
            val liveData = searchViewModel.subscribeToLiveData()

            Mockito.`when`(repository.searchGithubAsync(SEARCH_QUERY)).thenReturn(
                SearchResponse(1, null)
            )

            try {
                liveData.observeForever(observer)
                searchViewModel.searchGitHub(SEARCH_QUERY)

                val value: ScreenState.Error = liveData.value as ScreenState.Error
                Assert.assertEquals(value.error.message, ERROR_TEXT)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun liveData_TestReturnValueIsEqualsTotalCount() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<ScreenState> {}
            val liveData = searchViewModel.subscribeToLiveData()
            Mockito.`when`(repository.searchGithubAsync(SEARCH_QUERY)).thenReturn(
                SearchResponse(1, listOf())

            )
            try {
                liveData.observeForever(observer)
                searchViewModel.searchGitHub(SEARCH_QUERY)
                val response = liveData.value as ScreenState.Working
                Assert.assertEquals(1, response.searchResponse.totalCount)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun coroutines_TestException() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<ScreenState> {}
            val liveData = searchViewModel.subscribeToLiveData()
            try {
                liveData.observeForever(observer)
                searchViewModel.searchGitHub(SEARCH_QUERY)
                val value: ScreenState.Error = liveData.value as ScreenState.Error
                Assert.assertEquals(value.error.message, EXCEPTION_TEXT)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }
    @Test
    fun coroutines_TestExceptionResponseNull() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<ScreenState> {}
            val liveData = searchViewModel.subscribeToLiveData()
            Mockito.`when`(searchViewModel.searchGitHub(SEARCH_QUERY)).thenReturn(null)
            try {
                liveData.observeForever(observer)
                searchViewModel.searchGitHub(SEARCH_QUERY)
                val value: ScreenState.Error = liveData.value as ScreenState.Error
                Assert.assertEquals(value.error.message, EXCEPTION_TEXT)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    companion object {
        private const val SEARCH_QUERY = "some query"
        private const val ERROR_TEXT = "Search results or total count are null"
        private const val EXCEPTION_TEXT = "Response is null or unsuccessful"
    }
}
package com.tibaes.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nhaarman.mockitokotlin2.whenever
import com.tibaes.search.data.repository.SearchRepositoryImpl
import com.tibaes.search.data.service.SearchService
import com.tibaes.search.domain.entity.Github
import com.tibaes.search.domain.entity.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.ConnectException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var service: SearchService

    private lateinit var repository: SearchRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        repository = SearchRepositoryImpl(service)
    }

    @Test
    fun `it should return success when service emit data`() = runBlocking {

        whenever(service.getSearchResultTotal("words")).thenReturn(customResponse)

        assert(repository.getSearchResult("words") is LiveData<Status<Github>>)
    }

    @Test
    fun `it should return error when service emit any exception`() {
        runBlocking {
            whenever(service.getSearchResultTotal("words")).thenAnswer { throw ConnectException() }

            assert(repository.getSearchResult("words") is LiveData<*>)
        }
    }
}
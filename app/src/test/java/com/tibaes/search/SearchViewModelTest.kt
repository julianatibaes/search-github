package com.tibaes.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.tibaes.search.domain.entity.Status
import com.tibaes.search.domain.repository.SearchRepository
import com.tibaes.search.presentation.SearchInteraction
import com.tibaes.search.presentation.SearchState
import com.tibaes.search.presentation.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: SearchRepository

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchViewModel(repository)
    }

    @Test
    fun `it should init state properly`() {
        Assert.assertNull(viewModel.state.value)
    }

    @Test
    fun `when opened it should emit data state if list is not empty`() = runBlocking {


    }
}
package com.tibaes.search.presentation

import com.tibaes.search.domain.entity.Item

sealed class SearchState {
    object LoadingState : SearchState()
    data class DataState(val data: List<Item>) : SearchState()
    data class ErrorState(val error: String) : SearchState()
    object EmptyState : SearchState()
}
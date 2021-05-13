package com.tibaes.search.presentation

sealed class SearchInteraction {
    data class SearchGithub(val words: String) : SearchInteraction()
}
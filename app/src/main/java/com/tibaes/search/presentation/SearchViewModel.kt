package com.tibaes.search.presentation

import androidx.lifecycle.*
import com.tibaes.search.data.repository.Resultado
import com.tibaes.search.data.repository.SearchRepositoryImpl
import com.tibaes.search.domain.entity.*

class SearchViewModel(private val repository: SearchRepositoryImpl) : ViewModel() {

    val state: LiveData<SearchState>
        get() = itemListState

    private val itemListState = MutableLiveData<SearchState>()
    private var itemList: List<Item> = listOf()
    private var words = ""

    fun interact(interaction: SearchInteraction) {
        when (interaction) {
            is SearchInteraction.SearchGithub -> searchGithub(words)
        }
    }

    fun searchGithub(words: String): LiveData<Resultado<Github?>> =
        repository.getSearchResult(words)

      /*  viewModelScope.launch {
          // itemListState.value = SearchState.LoadingState
            val status = repository.getSearchResult(words).value
            status?.onSuccess {
                itemList = it.items
                itemListState.value = if (itemList.isEmpty()) SearchState.EmptyState else
                    SearchState.DataState(itemList)
            }
            status?.onError {
                itemListState.value = SearchState.ErrorState(it)
            }
        }


    } */
}
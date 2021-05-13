package com.tibaes.search.domain.repository

import androidx.lifecycle.LiveData
import com.tibaes.search.data.repository.Resultado
import com.tibaes.search.domain.entity.Github

interface SearchRepository {
   // fun getSearchResult(words: String, page: Int, perPage: Int): LiveData<Status<Github>>
    fun getSearchResult(words: String): LiveData<Resultado<Github?>>

}
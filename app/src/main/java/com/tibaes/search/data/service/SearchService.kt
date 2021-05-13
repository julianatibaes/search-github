package com.tibaes.search.data.service

import com.tibaes.search.data.entity.GithubRemote
import com.tibaes.search.domain.entity.Github
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {

    @GET("/repositories")
    fun getSearchResultTotal(@Query("q") words: String): Response<Github>

    @GET("repositories")
    fun getSearchResult(
        @Query("q") words: String,
        @Query("page") offset: Int = 1,
        @Query("per_page") limit: Int = 10
    ): GithubRemote
}
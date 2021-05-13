package com.tibaes.search.di

import com.tibaes.search.data.repository.SearchRepositoryImpl
import com.tibaes.search.data.service.SearchService
import com.tibaes.search.domain.repository.SearchRepository
import com.tibaes.search.presentation.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "https://api.github.com/search/"

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    single<SearchService> { get<Retrofit>().create(SearchService::class.java) }
}

val repositoryModule = module {
    single<SearchRepositoryImpl> { SearchRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel<SearchViewModel> { SearchViewModel(get()) }
}

val appModules = listOf(
    retrofitModule, repositoryModule, viewModelModule
)
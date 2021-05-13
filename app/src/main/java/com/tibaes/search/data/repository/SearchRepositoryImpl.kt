package com.tibaes.search.data.repository

import androidx.lifecycle.liveData
import com.tibaes.search.data.service.SearchService
import java.net.ConnectException

sealed class Resultado<out R> {
    data class Success<out T>(val response: T?) : Resultado<T?>()
    data class Error(val responseError: Exception) : Resultado<Nothing>()
}

class SearchRepositoryImpl(
    private val service: SearchService
) {

     fun getSearchResult(words: String) =
        liveData {
            try {
                val result = service.getSearchResultTotal(words)
                if (result.isSuccessful) {
                    emit(Resultado.Success(response = result.body()))
                } else {
                    emit(Resultado.Error(responseError = Exception("Falha na busca")))
                }
            } catch (e: ConnectException) {
                emit(Resultado.Error(responseError =  Exception("Falha na comunicação com a API")))
            } catch (e: Exception) {
                emit(Resultado.Error(responseError =  Exception("Falha geral")))
            }
        }

}

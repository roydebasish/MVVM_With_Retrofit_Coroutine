package com.mofficial.mvvm_with_retrofit_coroutine.api

import com.mofficial.mvvm_with_retrofit_coroutine.models.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("quotes")
    suspend fun getQuotes(@Query("page") pageno : Int) : Response<QuoteResponse>
}
package com.mofficial.mvvm_with_retrofit_coroutine.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mofficial.mvvm_with_retrofit_coroutine.api.ApiService
import com.mofficial.mvvm_with_retrofit_coroutine.db.QuoteDatabse
import com.mofficial.mvvm_with_retrofit_coroutine.models.QuoteResponse
import com.mofficial.mvvm_with_retrofit_coroutine.utils.NetworkUtils

class QuoteRepository(
    private val apiService: ApiService,
    private val database: QuoteDatabse,
    private val context: Context
) {

    private val quotesLiveData = MutableLiveData<Response<QuoteResponse>>()

    //Simple Syntax
//    val quoteResult : LiveData<QuoteResponse> = quotesLiveData

    //With get() function
    val quoteResult : LiveData<Response<QuoteResponse>>
        get() = quotesLiveData

    suspend fun getQuotes(page:Int){

        if(NetworkUtils.isInternetAvailable(context)){

            try {
                val response = apiService.getQuotes(page)
                if (response.body() != null){
                    database.quoteDao().addQuotes(response.body()!!.results)
                    quotesLiveData.postValue(Response.Success(response.body()))
                }
            }catch (e : Exception){
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }

        }else{

            val quotes = database.quoteDao().getQuotes()
            val quotesresult = QuoteResponse(1,1,1,quotes,1,1)

            try {
                val response = apiService.getQuotes(page)
                if (response.body() != null){
                    database.quoteDao().addQuotes(response.body()!!.results)
                    quotesLiveData.postValue(Response.Success(quotesresult))
                }
            }catch (e : Exception){
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }

        }

    }
}
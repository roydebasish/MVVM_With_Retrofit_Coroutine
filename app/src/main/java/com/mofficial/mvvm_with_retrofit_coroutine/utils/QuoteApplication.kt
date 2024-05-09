package com.mofficial.mvvm_with_retrofit_coroutine.utils

import android.app.Application
import com.mofficial.mvvm_with_retrofit_coroutine.api.ApiService
import com.mofficial.mvvm_with_retrofit_coroutine.api.RetrofitHelper
import com.mofficial.mvvm_with_retrofit_coroutine.db.QuoteDatabse
import com.mofficial.mvvm_with_retrofit_coroutine.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var repository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiService = RetrofitHelper.getRetrofitInstance().create(ApiService::class.java)
        val database = QuoteDatabse.getDatabase(applicationContext)
        repository = QuoteRepository(apiService,database,applicationContext)
    }
}
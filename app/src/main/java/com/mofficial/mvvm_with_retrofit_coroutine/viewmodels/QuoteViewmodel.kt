package com.mofficial.mvvm_with_retrofit_coroutine.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mofficial.mvvm_with_retrofit_coroutine.models.QuoteResponse
import com.mofficial.mvvm_with_retrofit_coroutine.repository.QuoteRepository
import com.mofficial.mvvm_with_retrofit_coroutine.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewmodel(private val repository: QuoteRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getQuotes(1)
        }
    }

    val quotes : LiveData<Response<QuoteResponse>>
        get() = repository.quoteResult
}
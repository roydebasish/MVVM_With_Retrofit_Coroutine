package com.mofficial.mvvm_with_retrofit_coroutine.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.mofficial.mvvm_with_retrofit_coroutine.repository.QuoteRepository

class QuoteViewmodelFactory(private val repository: QuoteRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return QuoteViewmodel(repository) as T
    }
}
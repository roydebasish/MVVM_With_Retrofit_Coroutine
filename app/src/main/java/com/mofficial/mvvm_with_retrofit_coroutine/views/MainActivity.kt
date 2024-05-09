package com.mofficial.mvvm_with_retrofit_coroutine.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mofficial.mvvm_with_retrofit_coroutine.R
import com.mofficial.mvvm_with_retrofit_coroutine.repository.Response
import com.mofficial.mvvm_with_retrofit_coroutine.utils.QuoteApplication
import com.mofficial.mvvm_with_retrofit_coroutine.viewmodels.QuoteViewmodel
import com.mofficial.mvvm_with_retrofit_coroutine.viewmodels.QuoteViewmodelFactory

class MainActivity : AppCompatActivity() {

    lateinit var quoteViewmodel: QuoteViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).repository

        quoteViewmodel = ViewModelProvider(this,QuoteViewmodelFactory(repository)).get(QuoteViewmodel::class.java)

        quoteViewmodel.quotes.observe(this, Observer{

            when(it){
                is Response.Loading ->{
                    Toast.makeText(this,"Data Getting...",Toast.LENGTH_SHORT).show()
                }
                is Response.Success ->{
                    it.data?.let {
                        Toast.makeText(this,""+it.results.size.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
                is Response.Error ->{
                    Toast.makeText(this,""+it.errorMessage.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
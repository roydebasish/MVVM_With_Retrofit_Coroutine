package com.mofficial.mvvm_with_retrofit_coroutine.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mofficial.mvvm_with_retrofit_coroutine.models.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDatabse : RoomDatabase() {
    abstract fun quoteDao() : QuoteDao

    companion object{
        @Volatile
        private var INSTANCE : QuoteDatabse? = null

        fun getDatabase(context: Context): QuoteDatabse{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        QuoteDatabse::class.java,
                        "quoteDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}
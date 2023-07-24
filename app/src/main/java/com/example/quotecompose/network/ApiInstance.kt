package com.example.quotecompose.network

import com.example.quotecompose.utility.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    /*fun getApiInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }*/

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val instance : QuoteServices by lazy {
        retrofit.create(QuoteServices::class.java)
    }
}
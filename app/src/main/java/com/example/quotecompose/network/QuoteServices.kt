package com.example.quotecompose.network

import com.example.quotecompose.model.QuoteModule
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteServices {

    @GET("/quotes")
    suspend fun getQuote(@Query("page") page : Int) : Response<QuoteModule>
}
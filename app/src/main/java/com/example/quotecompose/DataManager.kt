package com.example.quotecompose

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotecompose.quotejsonmodule.QuoteJsonModelItem
import com.google.gson.Gson

object DataManager {
    var data : Array<QuoteJsonModelItem> = emptyArray()

    val currentPage = mutableStateOf(StatePages.LISTING)
    var currentQuote : QuoteJsonModelItem?= null

    var isDataCome  = mutableStateOf(false)
    fun getQuoteItem(context: Context){
        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<QuoteJsonModelItem>::class.java)
        isDataCome.value = true
    }

    fun switchPage(quoteJsonModelItem: QuoteJsonModelItem?){
        if (StatePages.LISTING == currentPage.value){
            currentQuote = quoteJsonModelItem
            currentPage.value = StatePages.DETAILS
        }else{
            currentPage.value = StatePages.LISTING
        }
    }
}
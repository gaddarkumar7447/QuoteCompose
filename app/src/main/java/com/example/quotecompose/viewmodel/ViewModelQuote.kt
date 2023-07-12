package com.example.quotecompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotecompose.model.Result
import com.example.quotecompose.network.ApiInstance
import com.example.quotecompose.network.QuoteServices
import kotlinx.coroutines.launch

class ViewModelQuote : ViewModel(){

    private val apiInstance: QuoteServices = ApiInstance.getApiInstance().create(QuoteServices::class.java)

    private val _mutableLiveData : MutableLiveData<List<Result>> = MutableLiveData()
    val liveData : LiveData<List<Result>> = _mutableLiveData

    fun getLiveData(){
        viewModelScope.launch {
            _mutableLiveData.postValue(apiInstance.getQuote(1).body()?.results)
        }
    }
}
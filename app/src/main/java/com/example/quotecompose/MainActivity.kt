package com.example.quotecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.quotecompose.screen.QuoteDetails
import com.example.quotecompose.screen.QuoteListItem
import com.example.quotecompose.screen.onClick
import com.example.quotecompose.viewmodel.ViewModelQuote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ViewModelQuote>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            DataManager.getQuoteItem(applicationContext)
        }
        setContent {
            ShowData()
        }
    }
}


@Composable
fun ShowData(){
    if (DataManager.isDataCome.value){
        if (DataManager.currentPage.value == StatePages.LISTING){
            //QuoteListItem(data = DataManager.data, onClick = {q -> onClick(q) })
            LazyColumn {
                items(DataManager.data) {
                    QuoteListItem(data = it) { quoteData  -> DataManager.switchPage(quoteData) }
                }
            }
        }else{
            QuoteDetails(DataManager.currentQuote!!)
        }
    }
}


enum class StatePages{
    LISTING, DETAILS
}
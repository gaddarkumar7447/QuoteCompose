package com.example.quotecompose

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quotecompose.screen.QuoteDataMVVM
import com.example.quotecompose.screen.QuoteDetails
import com.example.quotecompose.screen.QuoteListItem
import com.example.quotecompose.ui.theme.QuoteComposeTheme
import com.example.quotecompose.viewmodel.ViewModelQuote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
            //getDataFromMvvm(viewModelQuote = viewModel)
        }
    }
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun LoaderRecycle() {
    val state = produceState(initialValue = 0, producer = {
        while (true){
            delay(16)
            value = (value + 10) % 360
        }
    })

    Box(modifier = Modifier.fillMaxWidth(1f),
        contentAlignment = Alignment.Center,
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    imageVector = Icons.Default.Refresh,
                    modifier = Modifier.size(60.dp)
                        .rotate(state.value.toFloat()),
                    contentDescription = "refresh"
                )
                Text(text = "Loading..", textAlign = TextAlign.Center)
            }
        }
    )
}

@Composable
fun KeyBoardComposable() {
    val view = LocalView.current
    DisposableEffect(key1 = Unit, effect = {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(view)
            val isKeyBoardVisible = insets?.isVisible(WindowInsetsCompat.Type.ime())
            Log.d("keyboard", isKeyBoardVisible.toString())
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    })
}


@Composable
@Preview
fun DisposableEffect() {
    val state = remember { mutableStateOf(false) }

    DisposableEffect(key1 = state.value) {
        Log.d("started", "Launch effected started")
        onDispose {
            Log.d("started", "clean up side effects")
        }
    }

    Button(onClick = { state.value = !state.value }) {
        Text(
            text = "change state",
            fontFamily = FontFamily(Font(R.font.figtree_italic))
        )
    }
}


// launchEffect
@Composable
fun getDataFromMvvm(viewModelQuote: ViewModelQuote) {
    val quoteData = viewModelQuote.liveData.observeAsState(emptyList()).value
    LaunchedEffect(key1 = Unit) {
        viewModelQuote.getLiveData()
    }

    LazyColumn(content = {
        items(quoteData) {
            QuoteDataMVVM(data = it) { data ->

            }
        }
    })
}


@Composable
fun launchEffect() {
    val count = remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    val key = count.value % 3 == 0
    LaunchedEffect(key1 = key) {
        Log.d("TASK", count.toString())
    }
    Button(onClick = {
        count.value += 1
    }) {
        Text(text = "count")
    }
}

@Composable
@Preview
fun App() {
    val theme = remember { mutableStateOf(false) }
    QuoteComposeTheme(theme.value) {
        Button(onClick = {
            theme.value = !theme.value
        }) {
            Text(text = "change theme")
        }
    }
}

@Composable
fun ShowData() {
    if (DataManager.isDataCome.value) {
        if (DataManager.currentPage.value == StatePages.LISTING) {
            //QuoteListItem(data = DataManager.data, onClick = {q -> onClick(q) })
            LazyColumn {
                items(DataManager.data) {
                    QuoteListItem(data = it) { quoteData -> DataManager.switchPage(quoteData) }
                }
            }
        } else {
            QuoteDetails(DataManager.currentQuote!!)
        }
    }
}


enum class StatePages {
    LISTING, DETAILS
}
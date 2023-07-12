package com.example.quotecompose.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotecompose.DataManager
import com.example.quotecompose.R
import com.example.quotecompose.quotejsonmodule.QuoteJsonModelItem

@Composable
fun QuoteDetails(data : QuoteJsonModelItem) {
    BackHandler(

    ) {
        DataManager.switchPage(null)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        Color.White,
                        Color.Gray
                    )
                )
            )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier.padding(18.dp)
        ) {
            Row() {
                Image(
                    imageVector = Icons.Filled.FormatQuote,
                    contentDescription = "",
                    modifier = Modifier
                        .size(70.dp)
                        .rotate(180f)
                )
            }
            Column() {
                Text(
                    text = data.text!!,
                    modifier = Modifier.padding(20.dp,0.dp, 5.dp, 2.dp),
                    fontFamily = FontFamily(Font(R.font.figtree_bold)),
                    fontSize = 18.sp
                )

                Text(
                    text = data.author ?: "null",
                    modifier = Modifier.padding(20.dp,8.dp, 0.dp, 15.dp),
                    fontFamily = FontFamily(Font(R.font.figtree_italic)),
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun showDDD() {
    QuoteDetails(data = QuoteJsonModelItem("Gaddar Kumar", "This is Gaddar Kumar Chaudhary This is Gaddar Kumar Chaudhary "))
}
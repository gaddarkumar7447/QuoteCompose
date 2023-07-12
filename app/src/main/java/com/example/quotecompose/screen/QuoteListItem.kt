package com.example.quotecompose.screen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotecompose.DataManager
import com.example.quotecompose.R
import com.example.quotecompose.quotejsonmodule.QuoteJsonModelItem

/*@Composable
@Preview(showBackground = true, showSystemUi = true, heightDp = 103)
fun ListShow() {
    QuoteListItem(
        "Time is the most valuable thing a man can spend, Time is the most",
        "Gaddar Chaudhary",
        onClick = { quote, author -> onClick(quote, author) }
    )
}*/

fun onClick(data : QuoteJsonModelItem){
    DataManager.currentQuote = data
    Log.d("DATAC", data.toString())
}

@Composable
fun QuoteListItem(data : QuoteJsonModelItem, onClick : (data : QuoteJsonModelItem) -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClick(data)
            }
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                imageVector = Icons.Filled.FormatQuote,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(40.dp)
                    .rotate(180f),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = data.text!!,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 6.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.figtree_bold))
                )

                Box(
                    modifier = Modifier
                        .background(Color(0xFFCCC2DC))
                        .fillMaxWidth(.4f)
                        .height(1.dp)
                )
                Text(
                    text = data.author ?: "null",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp),
                    fontFamily = FontFamily(Font(R.font.figtree_italic))
                )
            }
        }
    }
}
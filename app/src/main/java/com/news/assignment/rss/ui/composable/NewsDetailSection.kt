package com.news.assignment.rss.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NewsDetailSection(title: String, imageUrl: String, description: String) {
    Column {
        Text(
            "$title",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )
        RemoteImageFromUrl(
            imageUrl = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(
            "$description",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
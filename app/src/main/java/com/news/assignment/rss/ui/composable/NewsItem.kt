package com.news.assignment.rss.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.news.assignment.rss.R

@Composable
fun NewsItem(title: String, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_large))
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_medium)))
            .background(Color.DarkGray)
            .padding(dimensionResource(R.dimen.padding_medium))
            .clickable { onClick.invoke() }
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            text = title,
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.font_size_large).value.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        )
    }
}

@Composable
@Preview
fun NewsItem_Preview() {
    NewsItem(
        "News title goes here...",
    )
}
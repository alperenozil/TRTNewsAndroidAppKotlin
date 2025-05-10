package com.news.assignment.rss.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.news.assignment.rss.R


@androidx.compose.runtime.Composable
fun NewsItem(title: String, onClick: () -> Unit = {}) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.padding_medium)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium)),
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick.invoke() }
                .padding(dimensionResource(R.dimen.padding_large)),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.font_size_medium).value.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
@Preview
fun NewsItem_Preview() {
    NewsItem(title = "", onClick = {}
    )
}
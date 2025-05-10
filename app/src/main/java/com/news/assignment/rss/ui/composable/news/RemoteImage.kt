package com.news.assignment.rss.ui.composable.news

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.news.assignment.rss.R

@Composable
fun RemoteImageFromUrl(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
        contentScale = ContentScale.Crop
    )
}
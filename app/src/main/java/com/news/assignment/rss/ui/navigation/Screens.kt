package com.news.assignment.rss.ui.navigation

import android.net.Uri
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.news.assignment.rss.ui.composable.news.NewsItem
import com.news.assignment.rss.ui.composable.news.NewsDetailSection
import com.news.assignment.rss.ui.composable.news.SummarySection
import com.news.assignment.rss.ui.composable.settings.LanguageSelector
import com.news.assignment.rss.ui.viewmodel.chat.ChatViewModel
import com.news.assignment.rss.ui.viewmodel.news.NewsDetailViewModel
import com.news.assignment.rss.ui.viewmodel.news.NewsViewModel

@Composable
fun SettingsScreen(navController: NavController) {
    LanguageSelector()
}

@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = hiltViewModel()
) {
    val messages = remember {
        mutableStateOf(
            listOf(
                "Hi there! You can ask me anything about TRT World news.",
            )
        )
    }
    val newMessage = remember { mutableStateOf("") }
    val newsRecommendationState = chatViewModel.newsRecommendationState.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        LaunchedEffect(newsRecommendationState.value.uiData) {
            newsRecommendationState.value.uiData?.let {
                messages.value += it
            }
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages.value) { message ->
                Text(
                    text = message,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newMessage.value,
                onValueChange = { newMessage.value = it },
                label = { Text("Enter message") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (newMessage.value.isNotBlank()) {
                        chatViewModel.getRecommendation(
                            prompt = newMessage.value
                        )
                        messages.value += newMessage.value
                        newMessage.value = ""
                    }
                }
            ) {
                Text("Send")
            }
        }
    }
}

@Composable
fun DetailsScreen(
    navController: NavController,
    itemId: Int?,
    title: String,
    description: String,
    imageUrl: String,
    urlPath: String,
    newsDetailViewModel: NewsDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val newsSummaryState = newsDetailViewModel.newsSummaryState.collectAsState()
    Column {
        NewsDetailSection(title = title, imageUrl = imageUrl, description = description)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            println("alperenozil $urlPath $imageUrl")
            Text(
                text = "Go to website to read full article",
                color = Color.LightGray,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlPath))
                        context.startActivity(intent)
                    }
            )
            Button(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6392bb)
                ),
                onClick = {
                    newsDetailViewModel.getSummary(urlPath = urlPath)
                },
            ) {
                Text(
                    "Summarize",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            SummarySection(newsSummaryState = newsSummaryState)
        }
    }
}

@Composable
fun NewsScreen(navController: NavController, viewModel: NewsViewModel = hiltViewModel()) {
    val newsState = viewModel.newsState.collectAsState()
    val input: MutableState<String> = remember { mutableStateOf("") }
    val searchKeyword: MutableState<String> = remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = input.value,
                onValueChange = { newText ->
                    input.value = newText
                    if (newText.isEmpty()) {
                        searchKeyword.value = newText
                    }
                },
                label = { Text("Enter keyword") }
            )
            Button(
                modifier = Modifier.padding(start = 8.dp),
                shape = MaterialTheme.shapes.small,
                onClick = {
                    searchKeyword.value = input.value
                }) {
                Text("OK")
            }
        }
        if (newsState.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        LazyColumn(Modifier.fillMaxHeight()) {
            newsState.value.let { news ->
                news.items?.let {
                    items(it.filter {
                        it.title?.lowercase()?.contains(searchKeyword.value) == true
                    }) { item ->
                        val isClickable = !item.title.isNullOrBlank() &&
                                !item.description.isNullOrBlank() &&
                                !item.mainImageUrl.isNullOrBlank() &&
                                !item.path.isNullOrBlank()

                        NewsItem(
                            title = item.title ?: "Loading...",
                            enabled = isClickable,
                            onClick = {
                                if (isClickable) {
                                    val encodedTitle = Uri.encode(item.title)
                                    val encodedDesc = Uri.encode(item.description)
                                    val encodedImage = Uri.encode(item.mainImageUrl)
                                    val encodedPath = Uri.encode(item.path)
                                    navController.navigate("details/${item.id}/$encodedTitle/$encodedDesc/$encodedImage/$encodedPath")
                                }
                            }
                        )

                    }
                }
            }
        }
        if (newsState.value.error?.isNotBlank() == true) {
            Text("Error!")
        }
    }
}
package com.news.assignment.rss.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.news.assignment.rss.ui.state.UiDataState

@Composable
fun SummarySection(newsSummaryState: State<UiDataState>) {
    if (newsSummaryState.value.isLoading || newsSummaryState.value.uiData?.isNotBlank() == true) {
        Card(
            modifier = Modifier
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (newsSummaryState.value.isLoading) {
                    Text("Please wait while article is being summarized...")
                }
                if (newsSummaryState.value.uiData?.isNotBlank() == true) {
                    Text(
                        text = newsSummaryState.value.uiData.toString(),
                    )
                }
            }
        }
    } else {
        Box(modifier = Modifier) {}
    }
}

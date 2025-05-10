package com.news.assignment.rss.ui.composable.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.news.assignment.rss.ui.viewmodel.settings.LanguageViewModel

@Composable
fun LanguageSelector(viewModel: LanguageViewModel = hiltViewModel()) {
    val selectedLanguage by viewModel.selectedLanguage
    val languageOptions = listOf("en", "tr", "de", "fr", "es")
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Select Language", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(selectedLanguage)
                Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown Arrow")
            }


            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                languageOptions.forEach { lang ->
                    DropdownMenuItem(text = { Text(lang) }, onClick = {
                        viewModel.onLanguageSelected(lang)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

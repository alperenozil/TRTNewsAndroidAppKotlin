package com.news.assignment.rss.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@SuppressLint("RememberReturnType")
@Composable
fun BottomNavBar() {
    val rootNavController = rememberNavController()
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by rootNavController.currentBackStackEntryAsState()
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = {
                        setSelectedTab(0)
                        rootNavController.navigate("news")
                        {
                            popUpTo(rootNavController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                    },
                    label = { Text("Home") },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = {
                        setSelectedTab(1)
                        rootNavController.navigate("chat")
                        {
                            popUpTo(rootNavController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = { Text("Chat") },
                    icon = { Icon(imageVector = Icons.Default.List, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = {
                        setSelectedTab(2)
                        rootNavController.navigate("settings")
                        {
                            popUpTo(rootNavController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = { Text("Settings") },
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) }
                )
            }
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            NavHost(navController = rootNavController, startDestination = "news"){
                composable(route = "news"){ NewsNavHost() }
                composable(route = "chat"){ ChatNavHost() }
                composable(route = "settings"){ SettingsNavHost() }
            }
        }
    }
}

// her sekmenin kendi navhostu olmalı
@Composable
fun NewsNavHost(){
    val newsNavController = rememberNavController()
    NavHost(
        navController = newsNavController,
        startDestination = "news"
    ) {
        composable("news") { NewsScreen(newsNavController) }
        composable(
            route = "details/{itemId}/{title}/{description}/{imageUrl}/{urlPath}",
            arguments = listOf(
                navArgument("itemId") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("imageUrl") { type = NavType.StringType },
                navArgument("urlPath") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
            val urlPath = backStackEntry.arguments?.getString("urlPath") ?: ""
            DetailsScreen(newsNavController,itemId,title,description,imageUrl,urlPath)
        }
    }
}

@Composable
fun ChatNavHost(){
    val favNavController = rememberNavController()
    NavHost(
        navController = favNavController,
        startDestination = "chat"
    ) {
        composable("chat") { ChatScreen(favNavController) }
    }
}

@Composable
fun SettingsNavHost(){
    val settingsNavHost = rememberNavController()
    NavHost(
        navController = settingsNavHost,
        startDestination = "settings"
    ) {
        composable("settings") { SettingsScreen(settingsNavHost) }
    }
}

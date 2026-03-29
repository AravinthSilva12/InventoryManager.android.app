package com.aravinth.inventorymanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aravinth.inventorymanager.ui.navigation.MainScreen
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.ui.theme.InventoryManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InventoryManagerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Main.route,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(Screen.Main.route) {
                        MainScreen()
                    }
                    // You can add more screens here as you build them
                }
            }
        }
    }
}

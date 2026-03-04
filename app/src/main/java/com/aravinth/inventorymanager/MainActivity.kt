package com.aravinth.inventorymanager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aravinth.inventorymanager.ui.navigation.MainScreen
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.ui.theme.InventoryManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContent {
            InventoryManagerTheme() {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = Screen.Main.route) {
                    composable(Screen.Main.route) {
                        MainScreen()
                    }
                }
            }
        }
    }
}
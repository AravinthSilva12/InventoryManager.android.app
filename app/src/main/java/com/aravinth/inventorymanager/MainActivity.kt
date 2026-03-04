package com.aravinth.inventorymanager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aravinth.inventorymanager.ui.navigation.MainScreen
import com.aravinth.inventorymanager.ui.theme.InventoryManagerTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.ui.screen.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContent {
            InventoryManagerTheme() {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = Screen.Splash.route) {
                    composable(Screen.Splash.route) { SplashScreen(navController) }
                    composable(Screen.Main.route) {
                        MainScreen()
                    }
                }
            }
        }
    }
}
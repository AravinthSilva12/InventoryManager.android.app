package com.aravinth.inventorymanager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aravinth.inventorymanager.ui.navigation.MainScreen
import com.aravinth.inventorymanager.ui.theme.InventoryManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContent {
            InventoryManagerTheme() {
            MainScreen()
            }
        }
    }
}
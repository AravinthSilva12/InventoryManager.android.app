package com.aravinth.inventorymanager.ui.navigation

import android.graphics.drawable.Icon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.Composable

data class BottomNavItem(
    val screen: Screen,
    val icon: ImageVector,
    val label: String
)

@Composable
fun BottomNavigationBar(navController: NavHostController){
    val items = listOf(BottomNavItem(Screen.Home, Icons.Default.Home, "Home"),
    BottomNavItem(Screen.Stock, Icons.Default.List, "Stock"),
    BottomNavItem(Screen.Billing, Icons.Default.ShoppingCart, "Billing"),
    BottomNavItem(Screen.Suppliers, Icons.Default.person, "Suppliers"),
    BottomNavItem(Screen.CRM, Icons.Default.Star, "CRM")
    )
}
val currentRoute = navController.currentBackStateEntryAsState().value?.destination?.route

     NavigationBar {
         items.forEach { item ->
         }
     }

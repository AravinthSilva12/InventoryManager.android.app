package com.aravinth.inventorymanager.ui.navigation
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.aravinth.inventorymanager.ui.screen.*

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Stock.route) { StockScreen() }
            composable(Screen.Billing.route) { BillingScreen() }
            composable(Screen.Suppliers.route) { SuppliersScreen() }
            composable(Screen.CRM.route) { CrmScreen() }
        }
    }
}
@Composable
fun SuppliersScreen() {
    TODO("Not yet implemented")
}
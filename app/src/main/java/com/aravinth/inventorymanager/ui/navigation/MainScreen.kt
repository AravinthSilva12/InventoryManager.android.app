package com.aravinth.inventorymanager.ui.navigation
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aravinth.inventorymanager.ui.screen.BillingScreen
import com.aravinth.inventorymanager.ui.screen.CrmScreen
import com.aravinth.inventorymanager.ui.screen.HomeScreen
import com.aravinth.inventorymanager.ui.screen.StockScreen
import com.aravinth.inventorymanager.ui.screen.SupplierScreen

@Composable
fun MainScreen(){
    val navController = rememberNavController()
Scaffold( bottomBar = { BottomNavigationBar(navController) } ) { innerPadding ->
    NavHost(navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
           ) {
        composable(Screen.Home.route ) { HomeScreen() }
        composable(Screen.Stock.route) { StockScreen() }
        composable(Screen.Billing.route) { BillingScreen() }
        composable(Screen.Suppliers.route) { SupplierScreen() }
        composable(Screen.CRM.route) { CrmScreen() }
    }
  }
}
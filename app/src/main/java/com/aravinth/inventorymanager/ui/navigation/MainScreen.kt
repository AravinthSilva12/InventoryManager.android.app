package com.aravinth.inventorymanager.ui.navigation
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aravinth.inventorymanager.ui.screen.AddStockScreen
import com.aravinth.inventorymanager.ui.screen.BillingScreen
import com.aravinth.inventorymanager.ui.screen.CrmScreen
import com.aravinth.inventorymanager.ui.screen.EditStockScreen
import com.aravinth.inventorymanager.ui.screen.HomeScreen
import com.aravinth.inventorymanager.ui.screen.StockDetailScreen
import com.aravinth.inventorymanager.ui.screen.StockScreen
import com.aravinth.inventorymanager.ui.screen.SupplierScreen

@Composable
fun MainScreen(){
    val navController = rememberNavController()
Scaffold(  contentWindowInsets = WindowInsets.safeDrawing, bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
    NavHost(navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
           ) {
        composable(Screen.Home.route ) { HomeScreen() }
        composable(Screen.Stock.route) { StockScreen(navController) }
        composable(Screen.AddStock.route) { AddStockScreen(navController) }
        composable(route = "Stock_detail/{id}", arguments = listOf(navArgument("id") {type = NavType.IntType }))
                                 {backStateEntry -> val id = backStateEntry.arguments?.getInt("id")?:0
                                     StockDetailScreen(navController, id) }
        composable(route = "edit_stock/{id}", arguments = listOf(navArgument("id"){type = NavType.IntType}))
                                 {backStateEntry -> val id = backStateEntry.arguments?.getInt("id")?:0
                                     EditStockScreen(navController,id)
                                 }
        composable(Screen.Billing.route) { BillingScreen() }
        composable(Screen.Suppliers.route) { SupplierScreen() }
        composable(Screen.CRM.route) { CrmScreen() }
    }
  }
}
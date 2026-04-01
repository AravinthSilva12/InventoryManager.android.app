package com.aravinth.inventorymanager.ui.navigation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aravinth.inventorymanager.ui.screen.AddStockScreen
import com.aravinth.inventorymanager.ui.screen.BillHistoryScreen
import com.aravinth.inventorymanager.ui.screen.BillScreen
import com.aravinth.inventorymanager.ui.screen.CrmScreen
import com.aravinth.inventorymanager.ui.screen.EditStockScreen
import com.aravinth.inventorymanager.ui.screen.HomeScreen
import com.aravinth.inventorymanager.ui.screen.StockDataScreen
import com.aravinth.inventorymanager.ui.screen.StockDetailScreen
import com.aravinth.inventorymanager.ui.screen.StockScreen
import com.aravinth.inventorymanager.ui.screen.SupplierDataScreen
import com.aravinth.inventorymanager.ui.screen.SupplierScreen
import com.aravinth.inventorymanager.viewmodel.BillViewModel

@Composable
fun MainScreen(){
    val navController = rememberNavController()
Scaffold(
    bottomBar = {
        Box(modifier = Modifier.navigationBarsPadding())
        { BottomNavigationBar(navController) }
    }
) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }

            composable(Screen.Stock.route) { StockScreen(navController) }

            composable(Screen.AddStock.route) { AddStockScreen(navController) }

            composable (Screen.StockData.route) {StockDataScreen(navController)}

            composable(
                route = "Stock_detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            )
            { backStateEntry ->
                val id = backStateEntry.arguments?.getInt("id") ?: 0
                StockDetailScreen(navController, id)
            }

            composable(
                route = "edit_stock/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            )
            { backStateEntry ->
                val id = backStateEntry.arguments?.getInt("id") ?: 0
                EditStockScreen(navController, id)
            }

            composable(Screen.Billing.route) { BillScreen(navController) }

            composable (Screen.BillHistory.route ) {

                val viewModel: BillViewModel = viewModel()

                val bills by viewModel.bills.collectAsState(initial = emptyList())

                BillHistoryScreen(bills = bills,
                    onBillClick = { billId ->
                    // later navigate to detail:
                    }
                )
            }
            composable(Screen.Suppliers.route) { SupplierScreen(navController) }

            composable(Screen.SupplierData.route) { SupplierDataScreen(navController) }

            composable(Screen.CRM.route) { CrmScreen() }
        }
    }
}

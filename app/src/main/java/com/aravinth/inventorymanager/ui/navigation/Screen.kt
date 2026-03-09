package com.aravinth.inventorymanager.ui.navigation

sealed class Screen (val route : String) {
     object Splash:Screen("splash")
     object Main:Screen("main")

     object Home: Screen("home")
     object Stock: Screen("stock")
     object AddStock: Screen("add_stock")
     object Billing: Screen("billing")
     object Suppliers: Screen("suppliers")
     object CRM: Screen("crm")
}
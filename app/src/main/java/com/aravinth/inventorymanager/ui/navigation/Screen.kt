package com.aravinth.inventorymanager.ui.navigation

sealed class Screen (val route : String) {
     object Splash:Screen("splash")
     object Main:Screen("main")
     object Home: Screen("home")
     object Stock: Screen("stock")

     object StockData: Screen("stock_data")
     object AddStock: Screen("add_stock")

     object StockDetail: Screen("Stock_detail/{id}")

     object EditStock: Screen( "edit_stock/{id}")
     object Billing: Screen("billing")

     object BillHistory: Screen("bill_history")
     object Suppliers: Screen("suppliers")
     object CRM: Screen("crm")
}
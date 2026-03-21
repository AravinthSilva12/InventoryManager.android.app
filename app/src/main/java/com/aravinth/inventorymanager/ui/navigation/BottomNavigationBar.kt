package com.aravinth.inventorymanager.ui.navigation
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val screen: Screen,
    val icons: ImageVector,
    val label: String
)

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier, tonalElevation: Dp = 6.dp){
    val items = listOf(
        BottomNavItem(Screen.Stock, Icons.Default.List, "Stock"),
        BottomNavItem(Screen.Suppliers, Icons.Default.Person, "Suppliers"),
        BottomNavItem(Screen.Home, Icons.Default.Home, "Home"),
        BottomNavItem(Screen.Billing, Icons.Default.ShoppingCart, "Billing"),
        BottomNavItem(Screen.CRM, Icons.Default.Star, "CRM")
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(modifier = Modifier.navigationBarsPadding().padding(bottom = 8.dp),
                  tonalElevation = tonalElevation,
                  windowInsets = NavigationBarDefaults.windowInsets) {
        items.forEach{ item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = item.icons,
                        contentDescription = item.label,
                        modifier = Modifier.size(
                            if(item.screen == Screen.Home){30.dp}
                            else{24.dp}
                        ),
                    tint = if(currentRoute == item.screen.route) {
                        if(item.screen == Screen.Home) Color.Blue else Color.Black
                    } else{Color.Gray}
                    )
                },

                label = {
                    Text(item.label,
                        fontWeight = if(item.screen == Screen.Home)
                            FontWeight.SemiBold
                        else
                            FontWeight.Normal)
                }

            )
        }
    }
}



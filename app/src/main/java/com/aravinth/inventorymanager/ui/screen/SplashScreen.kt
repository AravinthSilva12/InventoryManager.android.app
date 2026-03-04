package com.aravinth.inventorymanager.ui.screen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aravinth.inventorymanager.R
import com.aravinth.inventorymanager.ui.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    var visible: Boolean by remember {mutableStateOf(false)}
    LaunchedEffect(Unit) {
        visible = true
        delay(1500)
        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(800))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splashscreenvector),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(160.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Inventory Manager",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
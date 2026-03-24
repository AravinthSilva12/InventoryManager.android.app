package com.aravinth.inventorymanager.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.viewmodel.BillViewModel

@Composable
fun BillScreen(navController: NavController){
    val billViewModel: BillViewModel = viewModel()
    val billItems by remember { mutableStateListOf<BillViewModel>( billViewModel.items ) }
    val total = billViewModel.loadItems()
    LaunchedEffect(Unit) { billViewModel.loadItems() }
    Scaffold(modifier = Modifier.fillMaxSize())
    { innerPadding->
        LazyColumn() { }






    }
}

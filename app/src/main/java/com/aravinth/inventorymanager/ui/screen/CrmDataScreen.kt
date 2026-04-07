package com.aravinth.inventorymanager.ui.screen

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.viewmodel.CrmViewModel
import kotlin.collections.emptyList
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.aravinth.inventorymanager.viewmodel.applicationViewModelFactory

@Composable
fun CrmDataScreen(navController: NavController) {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val viewModel: CrmViewModel = viewModel(
        factory = applicationViewModelFactory(application) {
            CrmViewModel(it) }
    )

    val customers by viewModel.customers.collectAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(customers) {customer ->

            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(4.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(customer.customerName, fontWeight = FontWeight.Bold)
                    Text(customer.customerPhone)
                    Text(customer.customerAddress)
                }
            }
        }
    }
}
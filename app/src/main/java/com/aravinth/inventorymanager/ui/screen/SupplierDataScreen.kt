package com.aravinth.inventorymanager.ui.screen

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.viewmodel.SupplierViewModel

@Composable
fun SupplierDataScreen(navController: NavController) {

    val context = LocalContext.current
    val application = context.applicationContext as Application

    val viewModel: SupplierViewModel = viewModel(factory = object : ViewModelProvider.Factory{
        override fun <T: ViewModel>
                create(modelClass: Class<T>): T{
            return SupplierViewModel(application) as T
        }
    }
    )

    val suppliers by viewModel.suppliers.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp).fillMaxSize())
    {
       Text("All Suppliers", fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(suppliers) {supplier ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)){
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(supplier.name, fontWeight = FontWeight.Bold)
                        Text(supplier.phone)
                        if(!supplier.address.isNullOrBlank()) {
                            Text(supplier.address)
                        }
                    }
                }
            }
        }
    }
}
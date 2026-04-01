package com.aravinth.inventorymanager.ui.screen
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.viewmodel.SupplierViewModel

@Composable
fun SupplierScreen(navController: NavController){
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val viewModel: SupplierViewModel = viewModel(factory = object:
        ViewModelProvider.Factory {
            override fun <T: ViewModel>
                    create(modelClass: Class<T>): T{
                return SupplierViewModel(application) as T
            }
        })

    val suppliers by viewModel.suppliers.collectAsState(initial = emptyList())

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize())
    {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {
           Text("Add Supplier", fontWeight = FontWeight.Bold)

            IconButton(onClick = {navController.navigate(Screen.SupplierData.route)
            }
            ) {
                Icon(imageVector = Icons.Default.ManageAccounts,
                    contentDescription = "View Suppliers")
            }
        }
                 Spacer(Modifier.height(8.dp))

        OutlinedTextField(value = name, onValueChange = { name = it}, label = {Text("Name")},
            modifier = Modifier.fillMaxWidth())

        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = {Text("Phone")},
            modifier = Modifier.fillMaxWidth())

        OutlinedTextField(value = address, onValueChange = { address = it }, label = {Text("Address")},
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (name.isNotBlank() && phone.isNotBlank()) {
                viewModel.addSupplier(name, phone, address)
                name = ""
                phone = ""
                address = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Add Supplier")
        }

        Spacer(Modifier.height(16.dp))

        Text("Suppliers", fontWeight = FontWeight.Bold)

        LazyColumn(modifier = Modifier.weight(1f)){
            items(suppliers) { supplier ->
                Text("${supplier.name} - ${supplier.phone}")
            }
        }
    }
}

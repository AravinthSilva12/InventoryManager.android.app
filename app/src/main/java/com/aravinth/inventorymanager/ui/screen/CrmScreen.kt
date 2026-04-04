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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.viewmodel.CrmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrmScreen(navController: NavController) {

    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel: CrmViewModel = viewModel(factory = object: ViewModelProvider.Factory{
        override fun <T: ViewModel>
                create(modelClass: Class<T>): T {
            return CrmViewModel(application) as T
        }
    })

    var customerName by remember { mutableStateOf("") }
    var customerPhone by remember { mutableStateOf("") }
    var customerAddress by remember { mutableStateOf("") }

    val customers by viewModel.customers.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(title = {Text("CRM")},
                actions = { IconButton(onClick = {navController.navigate("crm_list")}) {
                    Icon(imageVector = Icons.Default.PersonOutline, contentDescription = "View Customers")
                    }
                }
            )
        }
    )
    {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()
            .padding(16.dp))
        {
            Text("CRM", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = customerName,
                onValueChange = { customerName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = customerPhone,
                onValueChange = { customerPhone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = customerAddress,
                onValueChange = { customerAddress = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.addCustomer(
                        customerName = customerName,
                        customerPhone = customerPhone,
                        customerAddress = customerAddress,
                        purchaseDate = System.currentTimeMillis()
                    )
                    customerName = ""
                    customerPhone = ""
                    customerAddress = ""
                },
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text("Add Customer")
            }

            LazyColumn(modifier = Modifier.weight(1f))
            {
                items(customers) { customer ->
                    Text("${customer.customerName} - ${customer.customerPhone}")
                }
            }
        }
    }
}
package com.aravinth.inventorymanager.ui.screen
import android.R.attr.font
import android.R.attr.value
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aravinth.inventorymanager.domain.model.Crm

@Composable
fun CrmScreen() {

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("CRM", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = name, onValueChange = {name = it}, label = {Text("Name")}, modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = phone, onValueChange = {phone = it}, label = {Text("Phone")}, modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField
            value =
        )
    }
}
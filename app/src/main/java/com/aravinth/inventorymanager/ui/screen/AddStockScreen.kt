package com.aravinth.inventorymanager.ui.screen
import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.viewmodel.StockViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun AddStockScreen(navController: NavController)
{
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val focusManager = LocalFocusManager.current
    val viewModel: StockViewModel = viewModel(
        factory = object: ViewModelProvider.Factory{
            override fun <T: ViewModel>
                    create(modelClass: Class<T>): T {
                return StockViewModel(application) as T
            }
        }
    )
    // State variables :
    var name by remember { mutableStateOf("") }
    var purchasePrice by remember { mutableStateOf("") }
    var sellingPrice by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var reorderLevel by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)){
        Text(text = "Add Stock Item", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        //Name:
        OutlinedTextField(value = name, onValueChange = {name = it}, label = {Text("Item Name")},
         singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
         keyboardActions = KeyboardActions (onNext = {focusManager.moveFocus(FocusDirection.Down)}))

        Spacer(modifier = Modifier.height(12.dp))

        //Purchase price:
        OutlinedTextField(value = purchasePrice, onValueChange = {purchasePrice = it}, label = {Text("Purchasing price")},
         singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
         keyboardActions = KeyboardActions (onNext = {focusManager.moveFocus(FocusDirection.Down)}))

        Spacer(modifier = Modifier.height(12.dp))

        //Selling price:
        OutlinedTextField(value = sellingPrice, onValueChange = {sellingPrice = it}, label = {Text("Selling Price")},
         singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
         keyboardActions = KeyboardActions (onNext = {focusManager.moveFocus(FocusDirection.Down)}))

        Spacer(modifier = Modifier.height(12.dp))

        //Quantity:
        OutlinedTextField(value = quantity, onValueChange = {quantity = it}, label = {Text("Quantity of the item")},
         singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
         keyboardActions = KeyboardActions (onNext = {focusManager.moveFocus(FocusDirection.Down)}))

        Spacer(modifier = Modifier.height(12.dp))

        //Reorder level:
        OutlinedTextField(value = reorderLevel, onValueChange = {reorderLevel = it}, label = {Text("Re-Order level")},
         singleLine = true,keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
         keyboardActions = KeyboardActions (onDone = {focusManager.clearFocus()}))

        Spacer(modifier = Modifier.height(24.dp))

        //Save button:
        Button(onClick = {
                        if(name.isBlank() ||
                            purchasePrice.isBlank() ||
                            sellingPrice.isBlank() ||
                            quantity.isBlank() ||
                            reorderLevel.isBlank()){
                            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

            val purchase = purchasePrice.toDoubleOrNull()
            val selling = sellingPrice.toDoubleOrNull()
            val quant = quantity.toIntOrNull()
            val reOrder = reorderLevel.toIntOrNull()

               if(purchase == null || selling == null || quant == null || reOrder == null) {
                            Toast.makeText(context, "Invalid input values", Toast.LENGTH_SHORT).show()
                           return@Button
                        }

                        //Logical validation:
               if(quant <= 0) {Toast.makeText(context, "Quantity must be > 0", Toast.LENGTH_SHORT).show()
                           return@Button}
               if(purchase <= 0) {Toast.makeText(context, "Price must be > 0", Toast.LENGTH_SHORT).show()
                           return@Button}

            val item = StockItem(id = 0,
                                name = name,
                                purchasePrice = purchase,
                                sellingPrice = selling,
                                quantity = quant,
                                reorderLevel = reOrder,
                                supplierId = null)

                                viewModel.addStockItem(item)
            Toast.makeText(context, "Item added successfully", Toast.LENGTH_SHORT).show()

                                navController.popBackStack()
                            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
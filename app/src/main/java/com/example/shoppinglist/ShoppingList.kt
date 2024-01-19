package com.example.shoppinglist

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ShoppingList(){
    // Create a list of items
    var sItems by remember{ mutableStateOf(listOf<ShoppingItems>()) }
    var showDialog by remember{ mutableStateOf(false) }
    var itemName by remember{ mutableStateOf("") }
    var itemQuantity by remember{ mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.padding(8.dp))
        // Add a button to add items to the list
        Button(onClick = {
            showDialog = true
        }
        ){
            Text(text = "Add Item")
        }

        // Add a LazyColumn to display the list of items
        LazyColumn( modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){

            items(sItems){
                item ->

                


            }

        }
    }


    if(showDialog){
        // Add an AlertDialog to add items to the list
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Button(onClick = {
                                    if(itemName.isNotEmpty() && itemQuantity.isNotEmpty()){
                                        val newItem = ShoppingItems(
                                            id = sItems.size + 1,
                                            name = itemName,
                                            quantity = itemQuantity.toIntOrNull() ?: 0
                                        )
                                        sItems = sItems + newItem
                                        showDialog = false
                                        itemName = ""
                                        itemQuantity = ""
                                    }


                                }) {
                                    Text(text = "Add")
                                }

                                Button(onClick = {showDialog = false }) {
                                    Text(text = "Cancel")
                                }
                            }

            },
            title = { Text(text = "Add Item") },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {itemName = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {itemQuantity = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                }
                
            }
        )


    }

}

@Composable
fun ShoppingListItems(
    item: ShoppingItems,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(20)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = item.name , modifier = Modifier.padding(8.dp))
        Text(text  = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit , contentDescription = "Edit")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete , contentDescription = "Delete")
            }

        }
    }

}

@Composable
fun ShoppingItemEditor(item : ShoppingItems , onEditComplete: (String , Int) -> Unit) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
        )
    {

        Column {
            BasicTextField(
                value = editedName,
                onValueChange = {
                    editedName = it
                },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
                ) {

            }

            BasicTextField(
                value = editedQuantity,
                onValueChange = {
                    editedQuantity = it
                },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            ) {

            }
        }

        Button(onClick = {
            isEditing = false
            onEditComplete(editedName , editedQuantity.toIntOrNull() ?: 0 )
        }) {
            Text(text = "Save")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ShoppingListPreview(){
    ShoppingList()
}
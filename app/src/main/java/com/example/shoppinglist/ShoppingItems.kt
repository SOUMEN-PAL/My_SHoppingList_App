package com.example.shoppinglist

// Data class to hold the shopping items
data class ShoppingItems(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)

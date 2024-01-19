package com.example.shoppinglist

// Data class to hold the shopping items
data class ShoppingItems(
    val id: Int,
    val name: String,
    val quantity: Int,
    val isEditing: Boolean = false
)

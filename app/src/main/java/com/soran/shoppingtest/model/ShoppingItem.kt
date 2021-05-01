package com.soran.shoppingtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping")
data class ShoppingItem(
    val name: String,
    val amount: Int,
    val price: Float,
    val imgUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)

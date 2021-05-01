package com.soran.shoppingtest.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soran.shoppingtest.model.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1 ,exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingItemDao
}
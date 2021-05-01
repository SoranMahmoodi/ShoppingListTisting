package com.soran.shoppingtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soran.shoppingtest.model.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingItemDao
}
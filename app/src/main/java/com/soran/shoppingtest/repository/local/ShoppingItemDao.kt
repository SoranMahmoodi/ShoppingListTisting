package com.soran.shoppingtest.repository.local

import androidx.room.*
import com.soran.shoppingtest.model.ShoppingItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemShopping(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteItemShopping(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping")
    fun getItemsShopping(): Flow<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROM shopping ")
    fun getPriceShopping(): Flow<Float>
}
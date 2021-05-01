package com.soran.shoppingtest.repository

import com.soran.shoppingtest.model.ImageResponse
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoryShopping {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun getShoppingItems(): Flow<List<ShoppingItem>>

    fun geTotalPrice(): Flow<Float>

    suspend fun searchImage(searchImage:String):Resource<ImageResponse>
}
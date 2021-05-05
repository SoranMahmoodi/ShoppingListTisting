package com.soran.shoppingtest.data.local

import com.soran.shoppingtest.model.ImageResponse
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.repository.RepositoryShopping
import com.soran.shoppingtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRepositoryShoppingAndroidTest : RepositoryShopping {

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val getShoppingItems = MutableStateFlow<List<ShoppingItem>>(shoppingItems)

    private val getTotalPrice = MutableSharedFlow<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private suspend fun refreshFlow() {
        getShoppingItems.value = shoppingItems
        getTotalPrice.emit(getPriceFloat())
    }


    private fun getPriceFloat(): Float {
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()

    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshFlow()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshFlow()
    }

    override fun getShoppingItems(): Flow<List<ShoppingItem>> {
        return getShoppingItems
    }

    override fun geTotalPrice(): Flow<Float> {
        return getTotalPrice
    }

    override suspend fun searchImage(searchImage: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError){
            Resource.error("Error",null)
        }else{
            Resource.success(ImageResponse(listOf(),0,0))
        }
    }
}
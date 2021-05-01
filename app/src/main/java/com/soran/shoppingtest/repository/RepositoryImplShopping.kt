package com.soran.shoppingtest.repository

import com.soran.shoppingtest.model.ImageResponse
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.repository.local.ShoppingItemDao
import com.soran.shoppingtest.repository.network.ImageApi
import com.soran.shoppingtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImplShopping @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao,
    private val imageApi: ImageApi
) : RepositoryShopping {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDao.insertItemShopping(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDao.deleteItemShopping(shoppingItem)
    }

    override fun getShoppingItems(): Flow<List<ShoppingItem>> {
        return shoppingItemDao.getItemsShopping()
    }

    override fun geTotalPrice(): Flow<Float> {
        return shoppingItemDao.getPriceShopping()
    }

    override suspend fun searchImage(searchImage: String): Resource<ImageResponse> {
        return try {
            val response = imageApi.searchImage(searchImage)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("an unknown error occured ", null)
            } else {
                Resource.error("an unknown error occured ", null)
            }
        } catch (e: Exception) {
            Resource.error("check your internet connection $e", null)
        }
    }
}
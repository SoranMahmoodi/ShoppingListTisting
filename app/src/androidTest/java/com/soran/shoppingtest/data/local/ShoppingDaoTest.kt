package com.soran.shoppingtest.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.soran.shoppingtest.model.ShoppingItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.soran.shoppingtest.getOrAwaitValue
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class ShoppingDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: Database
    lateinit var shoppingItemDao: ShoppingItemDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()

        shoppingItemDao = database.getShoppingDao()
    }


    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertShoppingItemTest() = runBlockingTest {
        val shoppingItem = ShoppingItem("soran", 1, 1f, "image", 1)
        shoppingItemDao.insertItemShopping(shoppingItem)

        val shoppingItems = shoppingItemDao.getItemsShopping().asLiveData().getOrAwaitValue()

        assertThat(shoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItemTest() = runBlockingTest {
        val shoppingItem = ShoppingItem("soran", 1, 1f, "image", 1)
        shoppingItemDao.insertItemShopping(shoppingItem)
        shoppingItemDao.deleteItemShopping(shoppingItem)

        val shoppingItems = shoppingItemDao.getItemsShopping().asLiveData().getOrAwaitValue()

        assertThat(shoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun getPriceShoppingItem() = runBlockingTest {
        val shoppingItem1 = ShoppingItem("soran", 10, 5.5f, "image", 1)
        val shoppingItem2 = ShoppingItem("soran", 4, 10f, "image", 2)
        val shoppingItem3 = ShoppingItem("soran", 0, 5.5f, "image", 3)
        shoppingItemDao.insertItemShopping(shoppingItem1)
        shoppingItemDao.insertItemShopping(shoppingItem2)
        shoppingItemDao.insertItemShopping(shoppingItem3)

        val shoppingItems = shoppingItemDao.getPriceShopping().asLiveData().getOrAwaitValue()

        assertThat(shoppingItems).isEqualTo(10 * 5.5f + 4 * 40f)


    }
}

































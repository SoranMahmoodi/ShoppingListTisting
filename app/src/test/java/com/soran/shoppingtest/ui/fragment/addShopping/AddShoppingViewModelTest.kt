package com.soran.shoppingtest.ui.fragment.addShopping

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.google.common.truth.Truth.assertThat
import com.soran.shoppingtest.MainCoroutineRule
import com.soran.shoppingtest.getOrAwaitValueTest
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.repository.FakeRepositoryShopping
import com.soran.shoppingtest.utils.Constants
import com.soran.shoppingtest.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AddShoppingViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    lateinit var viewModel: AddShoppingViewModel


    @Before
    fun setup() {
        viewModel = AddShoppingViewModel(FakeRepositoryShopping())
    }

    @Test
    fun insert_shopping_item_with_empty_field_return_error() {
        viewModel.insertShoppingItem("soran", "", "5.5")

        val value = viewModel.insertShoppingItemStatus.asLiveData().getOrAwaitValueTest()

        assertThat(value.getContentIfHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insert_shopping_item_with_too_long_name_return_error() {
        val string = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem(string, "12", "2.2")

        val value = viewModel.insertShoppingItemStatus.asLiveData().getOrAwaitValueTest()

        assertThat(value.getContentIfHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insert_shopping_item_with_too_long_price_return_error() {
        val string = buildString {
            for (i in 1..Constants.MAX_PRICE_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem("soran", "45", string)

        val value = viewModel.insertShoppingItemStatus.asLiveData().getOrAwaitValueTest()

        assertThat(value.getContentIfHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insert_shopping_item_with_too_high_amount_return_error() {
        viewModel.insertShoppingItem("soran", "999999999999999999999999", "5")

        val value = viewModel.insertShoppingItemStatus.asLiveData().getOrAwaitValueTest()

        assertThat(value.getContentIfHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun insert_shopping_item_return_success() {
        viewModel.insertShoppingItem("soran","15","6.2")

        val value = viewModel.insertShoppingItemStatus.asLiveData().getOrAwaitValueTest()

        assertThat(value.getContentIfHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}
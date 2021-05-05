package com.soran.shoppingtest.ui.fragment.shopping

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.soran.shoppingtest.R
import com.soran.shoppingtest.adapter.ShoppingItemAdapter
import com.soran.shoppingtest.getOrAwaitValue
import com.soran.shoppingtest.launchFragmentInHiltContainer
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.ui.fragment.FragmentFactoryShoppingAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ShoppingFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidTest = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentShoppingFactory: FragmentFactoryShoppingAndroidTest

    @Before
    fun setup() {
        hiltAndroidTest.inject()
    }

    @Test
    fun swipeShoppingItem_deleteShoppingItem() {
        val shoppingItem = ShoppingItem("Benana", 10, 5.4f, "TEST", 1)
        var viewModelTest: ShoppingViewModel? = null
        launchFragmentInHiltContainer<ShoppingFragment>(fragmentFactory = fragmentShoppingFactory) {
            viewModelTest = viewModel
            viewModel?.insertShoppingItemToDp(shoppingItem)
        }

        onView(withId(R.id.rv_shoppingFragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ShoppingItemAdapter.ShoppingViewHolder>(
                0,
                swipeLeft()
            )
        )

        assertThat(viewModelTest?.shoppingItems?.asLiveData()?.getOrAwaitValue()).isEmpty()
    }


    @Test
    fun clickAddShoppingItem_navigationToFragmentShoppingAdd() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<ShoppingFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.fab_shopping_addShopping)).perform(click())

        verify(navController).navigate(
            ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingFragment()
        )
    }
}

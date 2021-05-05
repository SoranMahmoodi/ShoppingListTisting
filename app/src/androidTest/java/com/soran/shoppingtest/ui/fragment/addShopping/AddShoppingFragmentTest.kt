package com.soran.shoppingtest.ui.fragment.addShopping

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.soran.shoppingtest.R
import com.soran.shoppingtest.Utils.forceClick
import com.soran.shoppingtest.data.local.FakeRepositoryShoppingAndroidTest
import com.soran.shoppingtest.getOrAwaitValue
import com.soran.shoppingtest.launchFragmentInHiltContainer
import com.soran.shoppingtest.ui.base.FragmentFactoryShopping
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import com.google.common.truth.Truth.assertThat
import com.soran.shoppingtest.model.ShoppingItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalMultiplatform
class AddShoppingFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidTest = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: FragmentFactoryShopping

    @Before
    fun setup() {
        hiltAndroidTest.inject()
    }


    @Test
    fun clickBtnAddToInsertShoppingInToDB() {
        val viewModelTest = AddShoppingViewModel(FakeRepositoryShoppingAndroidTest())
        launchFragmentInHiltContainer<AddShoppingFragment>(fragmentFactory = fragmentFactory) {
            viewModel = viewModelTest
        }
        onView(withId(R.id.etShoppingItemName)).perform(replaceText("Benane"))
        onView(withId(R.id.etShoppingItemAmount)).perform(replaceText("15"))
        onView(withId(R.id.etShoppingItemPrice)).perform(replaceText("5.5"))
        onView(withId(R.id.btnAddShoppingItem)).perform(click())

       assertThat(viewModelTest.shoppingItems.asLiveData().getOrAwaitValue())
           .contains(ShoppingItem("Benane",15,5.5f,""))
    }


    @Test
    fun clickFragmentAddShopping_navigationToFragmentImagePick() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.ivShoppingImage)).perform(click())

        verify(navController).navigate(AddShoppingFragmentDirections.actionAddShoppingFragmentToImagePickFragment())
    }

    @Test
    fun pressBackButton_popBackStack() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddShoppingFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()
        verify(navController).popBackStack()
    }
}
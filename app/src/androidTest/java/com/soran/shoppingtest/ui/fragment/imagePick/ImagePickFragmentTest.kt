package com.soran.shoppingtest.ui.fragment.imagePick

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.soran.shoppingtest.R
import com.soran.shoppingtest.adapter.ImagePickAdapter
import com.soran.shoppingtest.data.local.FakeRepositoryShoppingAndroidTest
import com.soran.shoppingtest.launchFragmentInHiltContainer
import com.soran.shoppingtest.ui.base.FragmentFactoryShopping
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.android.synthetic.main.fragment_image_pick.view.*
import com.google.common.truth.Truth.assertThat
import com.soran.shoppingtest.getOrAwaitValue
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
class ImagePickFragmentTest {


    @get:Rule
    val instantTaskExecutorRule=InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidTest = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactoryShopping: FragmentFactoryShopping

    @Before
    fun setup() {
        hiltAndroidTest.inject()
    }

    @Test
    fun clickItemRvSetImage_navigationToAddShoppingFragment() {
        val navController = mock(NavController::class.java)
        val imageUrl = "TEST"
        val viewModelTest = ImagePickViewModel(FakeRepositoryShoppingAndroidTest())
        launchFragmentInHiltContainer<ImagePickFragment>(fragmentFactory = fragmentFactoryShopping) {
            Navigation.setViewNavController(requireView(), navController)
            imagePickAdapter.imageUrl = listOf(imageUrl)
            viewModel = viewModelTest
        }

        onView(withId(R.id.rv_imagePick)).perform(
            actionOnItemAtPosition<ImagePickAdapter.ImageViewHolder>(
                0,
                click()
            )
        )

        verify(navController).popBackStack()
        assertThat(viewModelTest.curImageUrl.asLiveData().getOrAwaitValue()).isEqualTo(imageUrl)
    }
}
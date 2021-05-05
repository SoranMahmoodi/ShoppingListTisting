package com.soran.shoppingtest.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.soran.shoppingtest.adapter.ImagePickAdapter
import com.soran.shoppingtest.adapter.ShoppingItemAdapter
import com.soran.shoppingtest.ui.fragment.addShopping.AddShoppingFragment
import com.soran.shoppingtest.ui.fragment.imagePick.ImagePickFragment
import com.soran.shoppingtest.ui.fragment.shopping.ShoppingFragment
import javax.inject.Inject

class FragmentFactoryShopping @Inject constructor(
    val imagePickAdapter: ImagePickAdapter,
    val glide: RequestManager,
    val shoppingItemAdapter: ShoppingItemAdapter
) :
    FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ShoppingFragment::class.java.name -> ShoppingFragment(shoppingItemAdapter)
            ImagePickFragment::class.java.name -> ImagePickFragment(imagePickAdapter)
            AddShoppingFragment::class.java.name -> AddShoppingFragment(glide)
            else -> super.instantiate(classLoader, className)

        }

    }
}
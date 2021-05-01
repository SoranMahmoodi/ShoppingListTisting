package com.soran.shoppingtest.ui.fragment.addShopping

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soran.shoppingtest.R

class AddShoppingFragment : Fragment(R.layout.fragment_add_shopping_item){

    private val viewModel : AddShoppingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
package com.soran.shoppingtest.ui.fragment.shopping

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soran.shoppingtest.R

class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    private val viewModel: ShoppingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
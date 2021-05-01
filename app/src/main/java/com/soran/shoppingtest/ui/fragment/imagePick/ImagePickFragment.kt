package com.soran.shoppingtest.ui.fragment.imagePick

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soran.shoppingtest.R

class ImagePickFragment : Fragment(R.layout.fragment_image_pick) {

    private val viewModel: ImagePickViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
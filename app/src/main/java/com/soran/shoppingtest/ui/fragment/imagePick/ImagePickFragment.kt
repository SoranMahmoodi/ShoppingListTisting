package com.soran.shoppingtest.ui.fragment.imagePick

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soran.shoppingtest.R
import com.soran.shoppingtest.adapter.ImagePickAdapter
import com.soran.shoppingtest.ui.fragment.addShopping.AddShoppingViewModel
import com.soran.shoppingtest.utils.Constants.SPAN_COUNT_IMAGE
import com.soran.shoppingtest.utils.OnClickItemImagePick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image_pick.*
import javax.inject.Inject


@AndroidEntryPoint
class ImagePickFragment @Inject constructor(val imagePickAdapter: ImagePickAdapter) :
    Fragment(R.layout.fragment_image_pick) {


    lateinit var viewModel: ImagePickViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ImagePickViewModel::class.java]
        setupViews()
    }

    private fun setupViews() {
        rv_imagePick.apply {
            adapter = imagePickAdapter
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT_IMAGE)
        }

        imagePickAdapter.setOnClickItemRv {
            viewModel.setCurImageUrl(it)
            findNavController().popBackStack()
        }
    }


}
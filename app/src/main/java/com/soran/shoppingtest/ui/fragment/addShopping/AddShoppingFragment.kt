package com.soran.shoppingtest.ui.fragment.addShopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.soran.shoppingtest.R
import com.soran.shoppingtest.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class AddShoppingFragment @Inject constructor(val glide: RequestManager) : Fragment() {

    lateinit var viewModel: AddShoppingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_shopping_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[AddShoppingViewModel::class.java]

        subscribeToObserve()


        ivShoppingImage.setOnClickListener {
            findNavController().navigate(AddShoppingFragmentDirections.actionAddShoppingFragmentToImagePickFragment())
        }

        val pressBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(pressBack)

        setupViews()
    }

    private fun setupViews() {
        btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                etShoppingItemName.text.toString(),
                etShoppingItemAmount.text.toString(),
                etShoppingItemPrice.text.toString()
            )
        }
    }

    private fun subscribeToObserve() {
        viewModel.curImageUrl.onEach {
            glide.load(it).into(ivShoppingImage)
        }.launchIn(lifecycleScope)

        viewModel.insertShoppingItemStatus.onEach {
            it.getContentIfHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        Snackbar.make(requireView().rootView, "successful", Snackbar.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()
                    }

                    Status.ERROR -> {
                        Snackbar.make(requireView().rootView, "unknowe $it", Snackbar.LENGTH_SHORT)
                            .show()

                    }

                    Status.EMPTY -> {

                    }

                    Status.LOADING -> {

                    }
                }
            }
        }.launchIn(lifecycleScope)
    }
}
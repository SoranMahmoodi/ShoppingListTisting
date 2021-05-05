package com.soran.shoppingtest.ui.fragment.shopping

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.soran.shoppingtest.R
import com.soran.shoppingtest.adapter.ShoppingItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shopping.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingFragment @Inject constructor(
    val shoppingItemAdapter: ShoppingItemAdapter,
    var viewModel: ShoppingViewModel? = null
) :
    Fragment(R.layout.fragment_shopping) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
        subscribeShoppingObserve()

        setupViews()

        fab_shopping_addShopping.setOnClickListener {
            findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingFragment())
        }
    }


    private fun setupViews() {
        rv_shoppingFragment.apply {
            adapter = shoppingItemAdapter
            layoutManager = LinearLayoutManager(requireContext())
            ItemTouchHelper(swipeItem).attachToRecyclerView(this)
        }
    }

    private val swipeItem = object : ItemTouchHelper.SimpleCallback(
        0, LEFT or RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true


        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val postion = viewHolder.adapterPosition
            val item = shoppingItemAdapter.shoppingItem[postion]
            viewModel?.deleteShoppingItem(item)
            Snackbar.make(requireView().rootView, "Success delete Item", Snackbar.LENGTH_LONG)
                .apply {
                    setAction("Undo") {
                        viewModel?.insertShoppingItemToDp(item)
                    }
                }.show()

        }

    }


    private fun subscribeShoppingObserve() {
        viewModel?.shoppingItems?.onEach {
            shoppingItemAdapter.shoppingItem = it
        }?.launchIn(lifecycleScope)
    }

}
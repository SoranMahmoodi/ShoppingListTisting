package com.soran.shoppingtest.ui.fragment.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.repository.RepositoryShopping
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(val repositoryShopping: RepositoryShopping) :
    ViewModel() {

    val shoppingItems = repositoryShopping.getShoppingItems()
    val totalPrice = repositoryShopping.geTotalPrice()

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repositoryShopping.deleteShoppingItem(shoppingItem)
    }


}
package com.soran.shoppingtest.ui.fragment.addShopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.repository.RepositoryShopping
import com.soran.shoppingtest.utils.Event
import com.soran.shoppingtest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddShoppingViewModel @Inject constructor(val repositoryShopping: RepositoryShopping) :
    ViewModel() {

    private val _insertShoppingItem = MutableSharedFlow<Event<Resource<ShoppingItem>>>()
    val insertShoppingItem: SharedFlow<Event<Resource<ShoppingItem>>> = _insertShoppingItem

    fun insertShoppingItemToDp(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repositoryShopping.insertShoppingItem(shoppingItem)
    }


    fun insertShoppingItem(nameString: String, amountString: String, priceString: String){

    }
}
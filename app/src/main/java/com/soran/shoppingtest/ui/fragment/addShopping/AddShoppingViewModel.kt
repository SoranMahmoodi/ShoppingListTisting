package com.soran.shoppingtest.ui.fragment.addShopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.repository.RepositoryShopping
import com.soran.shoppingtest.utils.Constants
import com.soran.shoppingtest.utils.Event
import com.soran.shoppingtest.utils.Resource
import com.soran.shoppingtest.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddShoppingViewModel @Inject constructor(private val repositoryShopping: RepositoryShopping) :
    ViewModel() {

    val shoppingItems = repositoryShopping.getShoppingItems()

    private val _insertShoppingItemStatus =
        MutableStateFlow<Event<Resource<ShoppingItem>>>(Event(Resource.empty()))
    val insertShoppingItemStatus: StateFlow<Event<Resource<ShoppingItem>>> =
        _insertShoppingItemStatus

    fun insertShoppingItemToDp(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repositoryShopping.insertShoppingItem(shoppingItem)
    }


    private val _curImageUrl = MutableStateFlow<String>("")
    val curImageUrl: StateFlow<String> = _curImageUrl


    private fun setCurImageUrl(urlImage: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _curImageUrl.emit(urlImage)
        }
    }

    fun insertShoppingItem(nameString: String, amountString: String, priceString: String) =
        viewModelScope.launch(Dispatchers.Main) {
            if (nameString.isEmpty() || amountString.isEmpty() || priceString.isEmpty()) {
                _insertShoppingItemStatus.value = Event(Resource.error("empty field", null))
                return@launch
            }

            if (nameString.length > Constants.MAX_NAME_LENGTH) {
                _insertShoppingItemStatus.value = Event(Resource.error("too long name", null))
                return@launch
            }

            if (priceString.length > Constants.MAX_PRICE_LENGTH) {
                _insertShoppingItemStatus.value = Event(Resource.error("too long price", null))
                return@launch
            }

            val amount = try {
                amountString.toInt()
            } catch (e: Exception) {
                _insertShoppingItemStatus.value = Event(Resource.error("high amount", null))
                return@launch
            }

            val shoppingItem =
                ShoppingItem(nameString, amount, priceString.toFloat(), _curImageUrl.value ?: "")
            insertShoppingItemToDp(shoppingItem)
            setCurImageUrl("")
            _insertShoppingItemStatus.value = Event(Resource.success(shoppingItem))
        }
}
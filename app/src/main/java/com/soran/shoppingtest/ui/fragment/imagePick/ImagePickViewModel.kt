package com.soran.shoppingtest.ui.fragment.imagePick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soran.shoppingtest.model.ImageResponse
import com.soran.shoppingtest.repository.RepositoryShopping
import com.soran.shoppingtest.utils.Event
import com.soran.shoppingtest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePickViewModel @Inject constructor(val repositoryShopping: RepositoryShopping) :
    ViewModel() {

    private val _image = MutableSharedFlow<Event<Resource<ImageResponse>>>()
    val image: SharedFlow<Event<Resource<ImageResponse>>> = _image


    fun searchForImage(imageQuery: String) {
        if (imageQuery.isEmpty()) {
            return
        }
        viewModelScope.launch {
            _image.emit(Event(Resource.loading(null)))
            val response = repositoryShopping.searchImage(imageQuery)
            _image.emit(Event(response))
        }
    }
}
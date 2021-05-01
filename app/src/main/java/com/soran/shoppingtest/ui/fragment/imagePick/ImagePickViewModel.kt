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

    private val _curImageUrl = MutableSharedFlow<String>()
    val curImageUrl: SharedFlow<String> = _curImageUrl


    fun setCurImageUrl(urlImage: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _curImageUrl.emit(urlImage)
        }
    }

    fun searchForImage(imageQuery:String) {

    }
}
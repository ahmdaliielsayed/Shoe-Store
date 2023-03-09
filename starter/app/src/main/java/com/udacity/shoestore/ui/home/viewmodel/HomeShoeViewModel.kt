package com.udacity.shoestore.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class HomeShoeViewModel : ViewModel() {

    private val _shoesMutableLiveData = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesLiveData: LiveData<MutableList<Shoe>>
        get() = _shoesMutableLiveData

    fun addNewShoe(shoeModel: Shoe) {
        _shoesMutableLiveData.value = (_shoesMutableLiveData.value ?: mutableListOf()).apply {
            add(shoeModel)
        }
    }
}

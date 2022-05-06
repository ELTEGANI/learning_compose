package com.example.test.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.Items
import com.example.test.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private var repository: Repository): ViewModel() {
   private val _items = MutableLiveData<List<Items>>()
   val Items : LiveData<List<Items>>
   get() = _items


    var list = listOf(
         Items(name = "Marc", price = "200")
        ,Items(name = "Toyota", price = "300")
        ,Items(name = "Marc", price = "200")
        ,Items(name = "Toyota", price = "300")
        ,Items(name = "Marc", price = "200")
        ,Items(name = "Toyota", price = "300")
        ,Items(name = "Marc", price = "200")
        ,Items(name = "Toyota", price = "300")
        ,Items(name = "Marc", price = "200")
        ,Items(name = "Toyota", price = "300")
        ,Items(name = "Marc", price = "200")
        ,Items(name = "Toyota", price = "300")
    )

    init {
        getList()
    }

    fun getList(){
        viewModelScope.launch {
            _items.value = list
        }
    }

}
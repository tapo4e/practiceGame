package com.example.practicegame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.practicegame.data.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private  val  dataBase: Data) : ViewModel() {
    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                val dataBase = (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).dataBase
                return MainViewModel(dataBase) as T
            }
        }
    }

    val exampleValue: Flow<Int> = dataBase.getScore

    fun saveValue(newValue: Int) {
        viewModelScope.launch {

            dataBase.saveScore(newValue)
        }
    }
}
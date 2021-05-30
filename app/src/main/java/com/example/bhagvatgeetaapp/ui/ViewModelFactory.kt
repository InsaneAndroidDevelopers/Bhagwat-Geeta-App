package com.example.bhagvatgeetaapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GeetaViewModel::class.java)) {
            return GeetaViewModel() as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
package com.example.bhagvatgeetaapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bhagvatgeetaapp.repository.GeetaRepository

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GeetaViewModel::class.java)) {
            return GeetaViewModel(GeetaRepository()) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
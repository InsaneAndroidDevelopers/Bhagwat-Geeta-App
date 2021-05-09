package com.example.bhagvatgeetaapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bhagvatgeetaapp.repository.GeetaRepository
import com.example.bhagvatgeetaapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class GeetaViewModel(private val repository: GeetaRepository) : ViewModel() {

    fun getAllChapters() = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try {
            emit(Resource.Success(data = repository.getChapterList()))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getChapterInfo(number: Int) = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try {
            emit(Resource.Success(data = repository.getChapterInfo(number)))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getVerseInfo(ch: Int, ver:Int) = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try {
            emit(Resource.Success(data = repository.getVerseInfo(ch, ver)))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getRandomVerse() = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try {
            emit(Resource.Success(data = repository.getRandomVerse()))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}

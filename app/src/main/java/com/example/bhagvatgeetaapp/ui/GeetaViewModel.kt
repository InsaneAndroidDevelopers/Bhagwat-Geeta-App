package com.example.bhagvatgeetaapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.bhagvatgeetaapp.repository.GeetaRepository
//import com.example.bhagvatgeetaapp.repository.GeetaRepository
import java.io.IOException


class GeetaViewModel(geetaRepository: GeetaRepository) : ViewModel() {

//    fun getAllChapters() = liveData(Dispatchers.IO){
//        emit(Resource.Loading(data = null))
//        try {
//            emit(Resource.Success(data = repository.getChapterList()))
//        } catch (exception: Exception) {
//            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

//    fun getChapterInfo(number: Int) = liveData(Dispatchers.IO){
//        emit(Resource.Loading(data = null))
//        try {
//            emit(Resource.Success(data = repository.getChapterInfo(number)))
//        } catch (exception: Exception) {
//            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }
//
//    fun getVerseInfo(ch: Int, ver:Int) = liveData(Dispatchers.IO){
//        emit(Resource.Loading(data = null))
//        try {
//            emit(Resource.Success(data = repository.getVerseInfo(ch, ver)))
//        } catch (exception: Exception) {
//            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }
//
//    fun getRandomVerse() = liveData(Dispatchers.IO){
//        emit(Resource.Loading(data = null))
//        try {
//            emit(Resource.Success(data = repository.getRandomVerse()))
//        } catch (exception: Exception) {
//            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

    fun getChaptersData(context: Context): String {
        val json: String
        try {
            val inputStream = context.assets.open("AllChapters.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.use { it.read(buffer) }
            json = String(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return json
    }

    fun getVersesData(context: Context): String {
        val json: String
        try {
            val inputStream = context.assets.open("AllVerses.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.use { it.read(buffer) }
            json = String(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return json
    }

}

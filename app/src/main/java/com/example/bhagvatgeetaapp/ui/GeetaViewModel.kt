package com.example.bhagvatgeetaapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
//import com.example.bhagvatgeetaapp.repository.GeetaRepository
import java.io.IOException


class GeetaViewModel() : ViewModel() {

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

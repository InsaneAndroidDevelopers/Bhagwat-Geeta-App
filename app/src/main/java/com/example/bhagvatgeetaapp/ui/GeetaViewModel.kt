package com.example.bhagvatgeetaapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.bhagvatgeetaapp.models.ChaptersItem
import com.example.bhagvatgeetaapp.models.VersesItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type


class GeetaViewModel : ViewModel() {

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

    fun provideChapters(context: Context) : ArrayList<ChaptersItem>{
        val data: String = getChaptersData(context)
        val type: Type = object : TypeToken<List<ChaptersItem?>?>() {}.type
        val chapters: List<ChaptersItem> = Gson().fromJson<List<ChaptersItem>>(data, type)
        return chapters as ArrayList<ChaptersItem>
    }

    fun provideVerses(context: Context) : ArrayList<VersesItem>{
        val data: String = getVersesData(context)
        val type: Type = object : TypeToken<List<VersesItem?>?>() {}.type
        val verses: List<VersesItem> = Gson().fromJson<List<VersesItem>>(data, type)
        return verses as ArrayList<VersesItem>
    }

    fun provideFilterVerses(num: Int, context: Context) : ArrayList<VersesItem>{
        val filterList = provideVerses(context).filter { it.chapter_number == num }
        return filterList as ArrayList<VersesItem>
    }

}

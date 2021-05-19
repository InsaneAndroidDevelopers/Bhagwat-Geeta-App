package com.example.bhagvatgeetaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bhagvatgeetaapp.adapters.AllChaptersAdapter
import com.example.bhagvatgeetaapp.models.ChaptersItem
import com.example.bhagvatgeetaapp.models.VersesItem
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import com.example.bhagvatgeetaapp.utils.Status
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_all_chapters.*
import java.lang.reflect.Type
import kotlin.coroutines.coroutineContext

class AllChapters : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    private lateinit var adapter: AllChaptersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_chapters)

        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)

        all_chapters_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = AllChaptersAdapter(applicationContext,provideChapters())
        all_chapters_recycler_view.adapter = adapter

    }

    private fun provideChapters() : ArrayList<ChaptersItem>{
        val data: String = viewModel.getChaptersData(applicationContext)
        val type: Type = object : TypeToken<List<ChaptersItem?>?>() {}.type
        val chapters: List<ChaptersItem> = Gson().fromJson<List<ChaptersItem>>(data, type)
        return chapters as ArrayList<ChaptersItem>
    }
}
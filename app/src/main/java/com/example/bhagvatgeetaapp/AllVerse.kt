package com.example.bhagvatgeetaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.bhagvatgeetaapp.adapters.AllChaptersAdapter
import com.example.bhagvatgeetaapp.adapters.AllVersesAdapter
import com.example.bhagvatgeetaapp.models.ChaptersItem
import com.example.bhagvatgeetaapp.models.VersesItem
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_all_chapters.*
import kotlinx.android.synthetic.main.activity_all_verse.*
import kotlinx.android.synthetic.main.shlok_card.*
import java.lang.reflect.Type

class AllVerse : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    private lateinit var adapter: AllVersesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_verse)

        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)
        val number = intent.getIntExtra("Number",0)
        val name = intent.getStringExtra("Name")

        Log.d("TAG", name.toString())

        all_verse_view.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        adapter = AllVersesAdapter(applicationContext,provideFilterVerses(number), name!!)
        all_verse_view.adapter = adapter

    }

    private fun provideVerses() : ArrayList<VersesItem>{
        val data: String = viewModel.getVersesData(applicationContext)
        val type: Type = object : TypeToken<List<VersesItem?>?>() {}.type
        val verses: List<VersesItem> = Gson().fromJson<List<VersesItem>>(data, type)
        return verses as ArrayList<VersesItem>
    }

    private fun provideFilterVerses(num: Int) : ArrayList<VersesItem>{
        val filterList = provideVerses().filter { it.chapter_number == num }
        return filterList as ArrayList<VersesItem>
    }
}
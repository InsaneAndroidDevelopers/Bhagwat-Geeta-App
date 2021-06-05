package com.example.bhagvatgeetaapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.bhagvatgeetaapp.adapters.AllVersesAdapter
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import kotlinx.android.synthetic.main.activity_all_verse.*

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
        adapter = AllVersesAdapter(applicationContext,viewModel.provideFilterVerses(number, this), name!!)
        all_verse_view.adapter = adapter

    }
}
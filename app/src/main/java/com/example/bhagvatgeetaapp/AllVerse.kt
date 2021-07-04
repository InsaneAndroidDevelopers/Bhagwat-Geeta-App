package com.example.bhagvatgeetaapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
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

        all_verse_view.clipChildren = false
        all_verse_view.clipToPadding = false
        all_verse_view.offscreenPageLimit = 3

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(20))
        compositePageTransformer.addTransformer { page, position->
            val r = 1 - Math.abs(position)
            page.scaleY = ((0.85 + r * 0.15f).toFloat())
        }
        all_verse_view.setPageTransformer(compositePageTransformer)

        adapter = AllVersesAdapter(applicationContext,viewModel.provideFilterVerses(number, this), name!!)
        all_verse_view.adapter = adapter

    }
}
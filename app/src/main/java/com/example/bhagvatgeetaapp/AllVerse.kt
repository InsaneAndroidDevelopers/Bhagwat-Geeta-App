package com.example.bhagvatgeetaapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.inflate
import android.widget.ImageView
import androidx.core.app.ShareCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
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
import java.io.File
import java.io.FileOutputStream
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
        adapter = AllVersesAdapter(applicationContext,viewModel.provideFilterVerses(number, this), name!!)
        all_verse_view.adapter = adapter

    }
}
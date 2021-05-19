package com.example.bhagvatgeetaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import com.example.bhagvatgeetaapp.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shlok_card.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    //var chapters = mutableListOf<Chapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)

//        btn_random.setOnClickListener {
//            getRandomVerse()
//        }
//        viewModel.getAllChapters().observe(this, Observer {
//            it?.let { resource ->
//                when(resource.status){
//                    Status.SUCCESS -> {
//                        resource.data?.let {
//                            Log.d("ALLCHAP", it.toString())
//                        }
//                    }
//                    Status.ERROR -> {
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    Status.LOADING -> {
//
//                    }
//                }
//            }
//        })

//        viewModel.getVerseInfo(7,9).observe(this, Observer {
//            it?.let { resource ->
//                when(resource.status){
//                    Status.SUCCESS -> {
//                        resource.data?.let {
//                            Log.d("INFO", it.toString())
//                        }
//                    }
//                    Status.ERROR -> {
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    Status.LOADING -> {
//
//                    }
//                }
//            }
//        })
//
//        viewModel.getChapterInfo(5).observe(this, Observer {
//            it?.let { resource ->
//                when(resource.status){
//                    Status.SUCCESS -> {
//                        resource.data?.let {
//                            Log.d("INFO", it.toString())
//                        }
//                    }
//                    Status.ERROR -> {
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    Status.LOADING -> {
//
//                    }
//                }
//            }
//        })
    }

    private fun setUpViews(chapter: Int, verse: Int, slok: String, meaning: String) {
        chapter_tv.text = "Chapter : ${chapter}"
        verse_tv.text = "Verse : ${verse}"
        shlokText.text = slok
        meaningText.text = meaning
    }

//    private fun getRandomVerse(){
//        viewModel.getRandomVerse().observe(this, Observer {
//            it?.let { resource ->
//                when(resource.status){
//                    Status.SUCCESS -> {
//                        resource.data?.let {
//                            setUpViews(it.chapter, it.verse, it.slok, it.tej.ht)
//                        }
//                    }
//                    Status.ERROR -> {
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    Status.LOADING -> {
//                    }
//                }
//            }
//        })
//    }
}
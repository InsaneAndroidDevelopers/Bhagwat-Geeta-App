package com.example.bhagvatgeetaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.bhagvatgeetaapp.models.VersesItem
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_verse.*
import java.lang.reflect.Type

class VerseActivity : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    private var chapter = 0
    private var verse = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verse)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)
        chapter = intent.getIntExtra("ChapterNum", -1)
        verse = intent.getIntExtra("VerseNum", -1)

        Log.d("TAG", "$chapter $verse")

        val verses = provideVerses().filter { it.chapter_number == chapter}
        val verseDis = verses.find { it.verse_number.toInt() == verse }

        if(chapter == -1 || verse == -1){
            val randomVerse = provideVerses().random()
            display(randomVerse)
        }else{
            display(verseDis!!)
        }
    }

    private fun display(verse: VersesItem){
        tv_chNum.text = "Chapter ${verse.chapter_number}"
        tv_verNum.text =  "Verse ${verse.verse_number}"
        tv_verText.text  = verse.text
        tv_meanText.text = verse.meaning
    }

    private fun provideVerses() : ArrayList<VersesItem>{
        val data: String = viewModel.getVersesData(applicationContext)
        val type: Type = object : TypeToken<List<VersesItem?>?>() {}.type
        val verses: List<VersesItem> = Gson().fromJson<List<VersesItem>>(data, type)
        return verses as ArrayList<VersesItem>
    }
}
package com.example.bhagvatgeetaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.bhagvatgeetaapp.models.VersesItem
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import kotlinx.android.synthetic.main.activity_verse.*

class VerseActivity : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    private var chapter = 0
    private var verse = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verse)
        supportActionBar!!.title = "Bhagwat Geeta"
        
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)

        chapter = intent.getIntExtra("ChapterNum", -1)
        verse = intent.getIntExtra("VerseNum", -1)

        val verses = viewModel.provideVerses(this).filter { it.chapter_number == chapter}
        val verseDis = verses.find { it.verse_number.toInt() == verse }

        if(chapter == -1 || verse == -1){
            val randomVerse = viewModel.provideVerses(this).random()
            display(randomVerse)
        }else{
            display(verseDis!!)
        }
    }

    private fun display(verse: VersesItem){
        tv_chNum.text = "Chapter ${verse.chapter_number}"
        tv_verseNum.text =  "Verse ${verse.verse_number}"
        tv_verseText.text  = verse.text.trim()
        tv_wordMeaning.text = verse.word_meanings.trim()
        tv_transliteration.text = verse.transliteration.trim()
        tv_meaning.text = verse.meaning.trim()
    }
}
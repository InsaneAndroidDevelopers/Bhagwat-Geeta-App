package com.example.bhagvatgeetaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.bhagvatgeetaapp.models.ChaptersItem
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    private var selectedChapter = 0
    private var selectedVerse = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)

        initViews()
        initButtons()

    }

    private fun initButtons(){
        btn_goToVerse.setOnClickListener {
            val intent = Intent(this, VerseActivity::class.java)
            intent.putExtra("ChapterNum", selectedChapter+1)
            intent.putExtra("VerseNum", selectedVerse+1)
            startActivity(intent)
        }

        btn_explore.setOnClickListener {
            val intent = Intent(this, AllChapters::class.java)
            startActivity(intent)
        }

        btn_goToRandom.setOnClickListener {
            val intent = Intent(this, VerseActivity::class.java)
            intent.putExtra("ChapterNum", -1)
            intent.putExtra("VerseNum", -1)
            startActivity(intent)
        }
    }

    private fun provideChapters() : ArrayList<ChaptersItem>{
        val data: String = viewModel.getChaptersData(applicationContext)
        val type: Type = object : TypeToken<List<ChaptersItem?>?>() {}.type
        val chapters: List<ChaptersItem> = Gson().fromJson<List<ChaptersItem>>(data, type)
        return chapters as ArrayList<ChaptersItem>
    }

    private fun makeVerseAdapter(selected: Int){
        val count = provideChapters().find { it.chapter_number == selected + 1 }!!.verses_count
        val verseArray = mutableListOf<String>()
        for(i in 0..count-1){
            verseArray.add(i, "Verse ${i+1}")
        }
        val versesAdapter = ArrayAdapter(this@MainActivity, R.layout.support_simple_spinner_dropdown_item, verseArray)
        sp_verses.adapter = versesAdapter
    }

    private fun initViews(){
        val chaptersArrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.chapters))
        sp_chapters.adapter = chaptersArrayAdapter

        sp_chapters.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedChapter = position
                makeVerseAdapter(selectedChapter)
            }
        }

        sp_verses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedVerse = position
                Toast.makeText(this@MainActivity, "Chapter -${selectedChapter+1} Verse - ${selectedVerse+1}", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
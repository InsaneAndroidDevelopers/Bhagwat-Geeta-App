package com.example.bhagvatgeetaapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    private var selectedChapter = 0
    private var selectedVerse = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)
        val chaptersArrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.chapters))
        sp_chapters.adapter = chaptersArrayAdapter

        initSpChapter()
        initSpVerse()
        initButtons()

    }

    private fun makeVerseAdapter(selected: Int){
        val count = viewModel.provideChapters(this).find { it.chapter_number == selected + 1 }!!.verses_count
        val verseArray = mutableListOf<String>()
        for(i in 0..count-1){
            verseArray.add(i, "Verse ${i+1}")
        }
        val versesAdapter = ArrayAdapter(this@MainActivity, R.layout.support_simple_spinner_dropdown_item, verseArray)
        sp_verses.adapter = versesAdapter
    }

    private fun initSpChapter(){

        sp_chapters.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedChapter = position
                makeVerseAdapter(selectedChapter)
            }
        }
    }

    private fun initSpVerse(){

        sp_verses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedVerse = position
                Toast.makeText(this@MainActivity, "Chapter -${selectedChapter+1} Verse - ${selectedVerse+1}", Toast.LENGTH_SHORT).show()
            }
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_Rate -> {
                Toast.makeText(applicationContext, "Rate", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_share -> {
                Toast.makeText(applicationContext, "Share", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_feedback-> {
                Toast.makeText(applicationContext, "Send Feedback", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_about -> {
                Toast.makeText(applicationContext, "About", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
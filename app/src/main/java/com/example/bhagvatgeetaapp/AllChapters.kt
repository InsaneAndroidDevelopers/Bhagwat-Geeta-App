package com.example.bhagvatgeetaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bhagvatgeetaapp.adapters.AllChaptersAdapter
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_all_chapters.*
import kotlinx.android.synthetic.main.chapter_info_extra.view.*

class AllChapters : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    private lateinit var adapter: AllChaptersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_chapters)
        supportActionBar!!.title = "Chapters"

        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)

        all_chapters_recycler_view.layoutManager = GridLayoutManager(this,2)
        adapter = AllChaptersAdapter(viewModel.provideChapters(this))
        all_chapters_recycler_view.adapter = adapter

        adapter.setOnItemClickListener {
            val ch = it
            val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val v = LayoutInflater.from(applicationContext).inflate(R.layout.chapter_info_extra,null)
            bottomSheetDialog.setContentView(v)
            v.ChapterNoTv.text = "Chapter ${it.chapter_number}"
            v.ChapterNameSanTv.text = it.name
            v.SummaryTv.text = it.chapter_summary
            v.readNowBtn.setOnClickListener {
                val intent = Intent(applicationContext, AllVerse::class.java)
                intent.putExtra("Number",ch.chapter_number )
                intent.putExtra("Name", ch.name)
                startActivity(intent)
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.show()
        }
    }
}
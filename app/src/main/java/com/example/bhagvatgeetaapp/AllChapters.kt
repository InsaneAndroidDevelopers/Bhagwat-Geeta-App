package com.example.bhagvatgeetaapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bhagvatgeetaapp.adapters.AllChaptersAdapter
import com.example.bhagvatgeetaapp.models.ChaptersItem
import com.example.bhagvatgeetaapp.ui.GeetaViewModel
import com.example.bhagvatgeetaapp.ui.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_all_chapters.*
import kotlinx.android.synthetic.main.chapter_info_extra.view.*
import java.lang.reflect.Type

class AllChapters : AppCompatActivity() {

    private lateinit var viewModel: GeetaViewModel
    private lateinit var adapter: AllChaptersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_chapters)

        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(GeetaViewModel::class.java)

        all_chapters_recycler_view.layoutManager = GridLayoutManager(this,2)
        adapter = AllChaptersAdapter(viewModel.provideChapters(this))
        all_chapters_recycler_view.adapter = adapter

        adapter.setOnItemClickListener {
            val ch = it
            val bottomSheetDialog = BottomSheetDialog(this)
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
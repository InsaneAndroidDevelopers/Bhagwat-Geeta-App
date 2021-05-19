package com.example.bhagvatgeetaapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagvatgeetaapp.AllVerse
import com.example.bhagvatgeetaapp.R
import com.example.bhagvatgeetaapp.models.ChaptersItem
import com.example.bhagvatgeetaapp.models.VersesItem
import kotlinx.android.synthetic.main.item_chapter_list.view.*
import kotlinx.android.synthetic.main.shlok_card.view.*

class AllVersesAdapter(val context: Context, val versesList: ArrayList<VersesItem>, val chapName: String) : RecyclerView.Adapter<AllVersesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllVersesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.shlok_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AllVersesAdapter.ViewHolder, position: Int) {
        holder.bindItems(versesList[position])
    }

    override fun getItemCount(): Int {
        return versesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(verse: VersesItem) {
            itemView.shlokText.text =  verse.text
            itemView.meaningText.text = verse.meaning
            itemView.chapter_name.text = chapName
            itemView.chapter_tv.text = "Chapter - ${verse.chapter_number}"
            itemView.verse_tv.text = "Verse - ${verse.verse_number}"
        }
    }
}
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
import kotlinx.android.synthetic.main.item_chapter_list.view.*

class AllChaptersAdapter(val context: Context, val chapterList: ArrayList<ChaptersItem>) : RecyclerView.Adapter<AllChaptersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllChaptersAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AllChaptersAdapter.ViewHolder, position: Int) {
        holder.bindItems(chapterList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AllVerse::class.java)
            intent.putExtra("Number", position+1)
            intent.putExtra("Name", chapterList[position].name)
            Log.d("TAG", chapterList[position].name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(chapter: ChaptersItem) {
            itemView.chapter_number.text = "Chapter - ${chapter.chapter_number.toString()}"
        }
    }
}
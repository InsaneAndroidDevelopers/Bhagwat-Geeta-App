package com.example.bhagvatgeetaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagvatgeetaapp.R
import com.example.bhagvatgeetaapp.models.ChaptersItem
import kotlinx.android.synthetic.main.item_chapter_list.view.*

class AllChaptersAdapter(private val chapterList: ArrayList<ChaptersItem>) : RecyclerView.Adapter<AllChaptersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllChaptersAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AllChaptersAdapter.ViewHolder, position: Int) {
        holder.bindItems(chapterList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(chapterList[position]) }
        }
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(chapter: ChaptersItem) {
            //itemView.chapter_number.text = "Chapter - ${chapter.chapter_number}"
            itemView.chapter_number.text = chapter.chapter_number.toString()
        }
    }

    private var onItemClickListener: ((ChaptersItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ChaptersItem) -> Unit) {
        onItemClickListener = listener
    }
}
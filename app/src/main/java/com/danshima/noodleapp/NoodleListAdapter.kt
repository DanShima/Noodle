package com.danshima.noodleapp

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danshima.noodleapp.data.Noodle
import kotlinx.android.synthetic.main.recyclerview_item.view.*

typealias ClickListener = (Int) -> Unit

class NoodleListAdapter(private val onClickListener: ClickListener) : RecyclerView.Adapter<NoodleListAdapter.WordViewHolder>() {
    private lateinit var context: Context
    private var noodles: List<Noodle>? = null // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val wordItemView: TextView = itemView.textView

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onClickListener(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (noodles != null) {
            val current = noodles!![position]
            holder.wordItemView.text = current.name
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.text = "No Word"
        }
    }

    fun setNoodles(noodles: List<Noodle>) {
        this.noodles = noodles
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // noodles has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (noodles != null)
            noodles!!.size
        else
            0
    }
}
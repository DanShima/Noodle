package com.danshima.noodleapp

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.danshima.noodleapp.data.Noodle
import kotlinx.android.synthetic.main.recyclerview_item.view.*

typealias ClickListener = (Int) -> Unit

class NoodleListAdapter(private val onClickListener: ClickListener) : RecyclerView.Adapter<NoodleListAdapter.NoodleViewHolder>() {
    private lateinit var context: Context
    private var noodles: List<Noodle>? = null

    inner class NoodleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val noodleInfo: TextView = itemView.noodle_info
        val noodleImage: ImageView = itemView.noodle_image
        val noodleTitle: TextView = itemView.noodle_title
        val noodleRestaurant: TextView = itemView.noodle_restaurant

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onClickListener(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoodleViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NoodleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoodleViewHolder, position: Int) {
        noodles?.let {
            val image = it[position]
            holder.apply {
                noodleInfo.text = image.description
                noodleImage.setImageDrawable(ContextCompat.getDrawable(context, image.photoID))
                noodleTitle.text = image.name
                noodleRestaurant.text = image.suggestedRestaurant
            }

        }
    }

    fun setNoodles(noodles: List<Noodle>) {
        this.noodles = noodles
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = noodles?.size ?: 0
}
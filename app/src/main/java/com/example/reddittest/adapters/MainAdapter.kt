package com.example.reddittest.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddittest.R
import com.example.reddittest.dc.Children
import com.example.reddittest.utils.RecyclerviewOnClickListener

class MainAdapter(
    private val listener: RecyclerviewOnClickListener,
    private val feeds: ArrayList<Children>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbnail)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)

        fun bind(children: Children) {
            tvTitle.text = children.data.title
            Glide.with(itemView.context)
                .load(children.data.thumbnail)
                .into(ivThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.feed_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = feeds.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(feeds[position])
        holder.imageButton.setOnClickListener(View.OnClickListener {
            listener.recyclerviewClick(feeds[position])
        })
        holder.itemView.setOnClickListener(View.OnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(feeds[position].data.url))
            startActivity(holder.itemView.context, browserIntent, null)
        })
    }

    fun addData(list: List<Children>) {
        feeds.clear()
        feeds.addAll(list)
        notifyDataSetChanged()
    }

}


package com.example.reddittest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reddittest.R
import com.example.reddittest.dc.ChildrenX

class CommentListAdapter(
    private val comments: ArrayList<ChildrenX>
) : RecyclerView.Adapter<CommentListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvComment: TextView = itemView.findViewById(R.id.tvComment)
        fun bind(childrenX: ChildrenX) {
            tvName.text = childrenX.data.author
            if ((childrenX.data.body == null) || (childrenX.data.body.trim().equals(""))) {
                tvComment.text = childrenX.data.title
            } else {
                tvComment.text = childrenX.data.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.comment_row, parent,
                false
            )
        )

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    fun addData(list: List<ChildrenX>) {
        comments.clear()
        comments.addAll(list)
        notifyDataSetChanged()
    }

}


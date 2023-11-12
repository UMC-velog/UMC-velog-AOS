package com.example.umc_velog_aos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_velog_aos.R
import com.example.umc_velog_aos.dto.PostDTO

class PostAdapter(private val context: Context, private val postList: List<PostDTO>)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTitle: TextView = itemView.findViewById(R.id.post_title)
        val postBody: TextView = itemView.findViewById(R.id.post_body)
        val postDate: TextView = itemView.findViewById(R.id.post_date)
        val postUser: TextView = itemView.findViewById(R.id.tv_post_user)
        val postLikes: TextView = itemView.findViewById(R.id.tv_likes)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = postList[position]

        holder.postTitle.text = currentPost.title
        holder.postBody.text = currentPost.body
        holder.postDate.text = currentPost.date
        holder.postUser.text = currentPost.user
        holder.postLikes.text = currentPost.likes.toString()
    }
    override fun getItemCount(): Int {
        return postList.size
    }
}
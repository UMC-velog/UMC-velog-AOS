package com.example.umc_velog_aos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.umc_velog_aos.R
import com.example.umc_velog_aos.dto.Post

interface OnItemClickListener {
    fun onItemClick(view: View, position: Int)
}
class PostAdapter(private val context: Context, private val postList: List<Post>)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postImage: ImageView = itemView.findViewById(R.id.post_image)
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

        //이미지 설정
        Glide.with(context)
            .load(currentPost.postImg)
            .into(holder.postImage)

        holder.postTitle.text = currentPost.title
        holder.postBody.text = currentPost.content
        holder.postDate.text = currentPost.createdDate
        //holder.postUser.text = currentPost.writer.username
        holder.postLikes.text = currentPost.likeCount.toString()
    }
    override fun getItemCount(): Int {
        return postList.size
    }
}
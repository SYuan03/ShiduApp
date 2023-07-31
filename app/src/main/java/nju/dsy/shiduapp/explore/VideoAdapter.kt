package nju.dsy.shiduapp.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nju.dsy.shiduapp.R

class VideoAdapter(
    private val videoList: List<VideoItem>,
    private val onItemClick: (VideoItem) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videocoverImageView: ImageView = itemView.findViewById(R.id.videocover)
        val titleTextView: TextView = itemView.findViewById(R.id.video_title)
        val authorTextView: TextView = itemView.findViewById(R.id.video_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoItem = videoList[position]
        holder.titleTextView.text = videoItem.title
        holder.authorTextView.text = videoItem.author
        // 在这里使用图片加载库加载封面图，例如 Glide
        Glide.with(holder.videocoverImageView).load(videoItem.coverUrl).into(holder.videocoverImageView)

        holder.itemView.setOnClickListener {
            // 对整个 item 设置点击事件
            onItemClick(videoItem)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}

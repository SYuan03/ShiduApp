package nju.dsy.shiduapp.music

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nju.dsy.shiduapp.R

class MusicAdapter(
    private val musicList: List<Music>,
    private val onPlayClickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val musicNameTextView: TextView = view.findViewById(R.id.musicNameTextView)
        val playButton: ImageButton = view.findViewById(R.id.playButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val music = musicList[position]
        holder.musicNameTextView.text = music.name
        holder.playButton.setOnClickListener { onPlayClickListener(position) }
    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}

package nju.dsy.shiduapp.weather

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import nju.dsy.shiduapp.R

class WeatherAdapter(private val weatherItems: List<WeatherItem>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    // ViewHolder用于保存视图的引用
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temperatureTextView: TextView = itemView.findViewById(R.id.wth_temperature)
        val timeTextView: TextView = itemView.findViewById(R.id.wth_time)
        val imageView: ImageView = itemView.findViewById(R.id.wth_image)
    }

    // 创建新的视图（由布局管理器调用）
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherItems[position]
        holder.timeTextView.text = weather.time
        holder.imageView.setImageResource(weather.image)
        holder.temperatureTextView.text = weather.temperature
    }
}


package nju.dsy.shiduapp.news

import androidx.room.PrimaryKey

// News.kt
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // 自增的id字段
    val title: String,          // 标题
    val imageUrl: String,       // 图片地址
    val summary: String,        // 摘要
    val url: String             // 新闻地址
)


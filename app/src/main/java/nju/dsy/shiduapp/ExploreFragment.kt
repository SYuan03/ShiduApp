package nju.dsy.shiduapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nju.dsy.shiduapp.explore.VideoAdapter
import nju.dsy.shiduapp.explore.VideoItem

class ExploreFragment : Fragment() {

    private lateinit var videoRecyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter

    private val videoList = listOf(
        VideoItem("完整版《ikun话》演唱会", "https://box.nju.edu.cn/f/0dc19474b57743f7b984/?dl=1", "https://box.nju.edu.cn/f/3bb3c56f4b8c4040a1fd/?dl=1", "B站-上海滩许Van强"),
        VideoItem("欧洲人也太容易快乐了", "https://box.nju.edu.cn/f/361f733e6dd74eb09b6f/?dl=1", "https://box.nju.edu.cn/f/3dde3b6e791b41b4ae95/?dl=1", "B站-一本正经的光头张"),
        // 添加更多视频项
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        videoRecyclerView = view.findViewById(R.id.videoRecyclerView)

        // 初始化RecyclerView和Adapter
        videoAdapter = VideoAdapter(videoList) { videoItem ->
            // 处理视频项点击事件，在这里跳转到视频播放界面
            val intent = Intent(context, VideoPlayerActivity::class.java)
            intent.putExtra("videoUrl", videoItem.videoUrl)
            startActivity(intent)
        }

        videoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = videoAdapter
        }

        return view
    }
}
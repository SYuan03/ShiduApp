package nju.dsy.shiduapp

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nju.dsy.shiduapp.music.Music
import nju.dsy.shiduapp.music.MusicAdapter


//class MusicFragment : Fragment() {
//    val TAG = "MusicFragment"
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.i(TAG, "onAttach")
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.i(TAG, "onCreate")
//
////        if (arguments != null) {
////            val value = arguments?.getString("key")
////            Log.i(TAG, "activity传过来的值:$value")
////        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        Log.i(TAG, "onCreateView")
//        val view = inflater.inflate(R.layout.fragment_music, container, false)
//
//        return view
//    }
//}
class MusicFragment : Fragment() {

    private lateinit var musicRecyclerView: RecyclerView
    private lateinit var musicAdapter: MusicAdapter
    private var currentPlayingPosition = -1

    private val musicList: MutableList<Music> = mutableListOf()

    private val originalMusicList = listOf(
        Music("大笑江湖 - 小沈阳", R.raw.music_daxiaojianghu),
        Music("Bones - Imagine Dragons", R.raw.music6),
        Music("Cure For Me - Aurora", R.raw.music5),
        Music("给你一瓶魔法药水 - 告五人", R.raw.music_geiniyiping),
        Music("If We Ever Broke Up - Mae Stephens", R.raw.music4),
        Music("Never Gonna Give You Up - Rick Astley", R.raw.music3),
    )

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)

        musicRecyclerView = view.findViewById(R.id.musicRecyclerView)

        // 初始化RecyclerView和Adapter
        musicAdapter = MusicAdapter(musicList) { position ->
            // 处理音乐播放逻辑
            val music = musicList[position]
            if (isPlaying) {
                stopMusic()
            } else {
                playMusic(music.resourceId, position)
            }
            // 更新播放按钮的图标
            musicAdapter.notifyItemChanged(position)
        }

        musicRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter

        }

        // 实现无限滚动
        musicRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    // 到达列表底部，可以加载更多数据
                    // 调用加载更多数据的方法
                    loadMoreData()
                }
            }
        })

        // 将原始音乐列表中的数据添加到音乐列表中
        loadMoreData()

        return view
    }
    private fun loadMoreData() {
        // 将新数据添加到音乐列表中
        musicList.addAll(originalMusicList)

        // 通知适配器数据发生变化
        musicAdapter.notifyDataSetChanged()
    }

    private fun playMusic(resourceId: Int, position: Int) {
        if (mediaPlayer != null && isPlaying && currentPlayingPosition == position) {
            // 如果当前有音乐在播放，且点击的是正在播放的音乐，则暂停音乐
            mediaPlayer?.pause()
            isPlaying = false
        } else {
            // 否则，停止当前正在播放的音乐（如果有），然后播放新的音乐
            stopMusic()
            mediaPlayer = MediaPlayer.create(context, resourceId)
            mediaPlayer?.setOnCompletionListener {
                stopMusic()
            }
            mediaPlayer?.start()
            isPlaying = true
            currentPlayingPosition = position
        }
        // 更新播放按钮的图标
        musicAdapter.notifyItemChanged(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
    }

    private fun stopMusic() {
        mediaPlayer?.pause()
        mediaPlayer?.seekTo(0)
        isPlaying = false
    }

}

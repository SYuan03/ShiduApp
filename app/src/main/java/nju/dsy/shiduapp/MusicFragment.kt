package nju.dsy.shiduapp

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    private val musicList = listOf(
        Music("daxiaojianghu", R.raw.music_daxiaojianghu),
        Music("stories", R.raw.music_stories),
        Music("the_ludlows", R.raw.music_the_ludlows),
        Music("geiniyiping", R.raw.music_geiniyiping),
    )

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
                playMusic(music.resourceId)
            }
            // 更新播放按钮的图标
            musicAdapter.notifyItemChanged(position)
        }

        musicRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = musicAdapter
        }

        return view
    }

    private fun playMusic(resourceId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, resourceId)
            mediaPlayer?.setOnCompletionListener {
                stopMusic()
            }
        }
        mediaPlayer?.start()
        isPlaying = true
    }

    private fun stopMusic() {
        mediaPlayer?.pause()
        mediaPlayer?.seekTo(0)
        isPlaying = false
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

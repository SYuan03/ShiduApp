package nju.dsy.shiduapp

import android.os.Bundle
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoUrl = intent.getStringExtra("videoUrl")

        videoView = findViewById(R.id.video_view)
        videoView.setVideoPath(videoUrl)

        val videoTitleTextView: TextView = findViewById(R.id.video_title)
        videoTitleTextView.text = intent.getStringExtra("videoTitle")

        val mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)

        videoView.start()
    }
}

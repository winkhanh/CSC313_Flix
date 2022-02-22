package winkhanh.com.flix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class MovieTrailer : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private var videoKey :String = ""
    private val apiKey = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_trailer)
        videoKey = intent.getStringExtra("key").toString()
        val ytplayerView : YouTubePlayerView = findViewById(R.id.ytplayer)
        ytplayerView.initialize(apiKey, this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.cueVideo(videoKey)
        p1?.play()
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.d("Detail Page","Youtube Error")
    }
}
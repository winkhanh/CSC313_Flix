package winkhanh.com.flix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.RoundedCorner
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.shape.RoundedCornerTreatment

import org.parceler.Parcels
import winkhanh.com.flix.models.Movie

class DetailPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)
        Log.d("Detail Page","startbbbbbbb")

        val tvTitle : TextView = findViewById(R.id.tvDetailTitle)
        val tvOverview : TextView = findViewById(R.id.tvDetailOverview)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val ivPoster : ImageView = findViewById(R.id.ivDetailPoster)
        Log.d("Detail Page","startbbbbbbb1")
        //unwrap
        val movie: Movie = Parcels.unwrap(intent.getParcelableExtra("movie"))
        Log.d("Detail Page","startbbbbbbb2")
        //set Content
        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        ratingBar.rating =  movie.score.toFloat()
        Glide.with(this).load(movie.backdropPath).placeholder(R.drawable.placeholder)
            .into(ivPoster)
        ivPoster.setOnClickListener {
            movie.getYoutubeId(this)
        }
    }

}
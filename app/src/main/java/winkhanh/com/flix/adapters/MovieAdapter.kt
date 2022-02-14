package winkhanh.com.flix.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import winkhanh.com.flix.R
import winkhanh.com.flix.models.Movie

class MovieAdapter(val context: Context, val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context).load(movie.posterPath).into(ivPoster)
        }

        val tvTitle : TextView
        val tvOverview : TextView
        val ivPoster : ImageView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvOverview = itemView.findViewById(R.id.tvOverview)
            ivPoster = itemView.findViewById(R.id.ivPoster)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val movieView: View = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

}
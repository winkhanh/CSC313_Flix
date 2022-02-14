package winkhanh.com.flix.adapters

import android.content.Context
import android.content.res.Configuration
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
    private val POPULAR = 1
    private val NONPOPULAR = 0

    abstract inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        open fun bind(movie: Movie){}
    }

    inner class ViewHolder1(itemView: View) : ViewHolder(itemView) {
        override fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                Glide.with(context).load(movie.backdropPath).placeholder(R.drawable.placeholder).into(ivPoster)
            }
            else{
                Glide.with(context).load(movie.posterPath).placeholder(R.drawable.placeholder).into(ivPoster)
            }

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
    inner class ViewHolder2(itemView: View) : ViewHolder(itemView) {
        override fun bind(movie: Movie) {
            Glide.with(context).load(movie.backdropPath).placeholder(R.drawable.placeholder).into(ivPoster)
        }
        val ivPoster : ImageView = itemView.findViewById(R.id.ivPoster)
    }

    override fun getItemViewType(position: Int): Int {
        return if (movies[position].score > 5 )
            POPULAR
        else
            NONPOPULAR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder : ViewHolder = if (viewType == POPULAR){
            val movieView: View = LayoutInflater.from(context).inflate(R.layout.item_movie2, parent, false)
            ViewHolder2(movieView)
        }else{
            val movieView: View = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
            ViewHolder1(movieView)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

}
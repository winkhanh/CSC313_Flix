package winkhanh.com.flix.models

import org.json.JSONArray
import org.json.JSONObject

class Movie(jsonObj: JSONObject) {
    private val prefixImageUrl = "https://image.tmdb.org/t/p/w342"
    val posterPath: String? = jsonObj.getString("poster_path")
        get()=prefixImageUrl+field
    val title: String = jsonObj.getString("title")
    val overview: String = jsonObj.getString("overview")
    val backdropPath: String? = jsonObj.getString("backdrop_path")
        get()=prefixImageUrl+field
    val score: Double = jsonObj.getDouble("vote_average")

    companion object{
        fun getMovies(jsonArray: JSONArray): MutableList<Movie> {
            val movies: MutableList<Movie> = ArrayList<Movie>()
            for (i in 0 until (jsonArray.length()-1)){
                movies.add(Movie(jsonArray.getJSONObject(i)))
            }
            return movies
        }
    }
}
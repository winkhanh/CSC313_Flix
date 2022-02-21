package winkhanh.com.flix.models

import android.content.Context
import android.content.Intent
import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import org.parceler.Parcels
import winkhanh.com.flix.DetailPageActivity
import winkhanh.com.flix.MainActivity
import winkhanh.com.flix.MovieTrailer
import winkhanh.com.flix.R

@Parcel
public class Movie constructor(jsonObj: JSONObject) {
    constructor():this(JSONObject())

    public val prefixImageUrl = "https://image.tmdb.org/t/p/w342"
    public var id : String? = ""
    public var posterPath: String? = ""
        get()=prefixImageUrl+field
    public var title: String = ""
    public var overview: String = ""
    public var backdropPath: String? = ""
        get()=prefixImageUrl+field
    public var score: Double = 0.0
    public var videoKey: String = ""
    init{
        if (jsonObj.has("id"))
            id = jsonObj.getString("id")
        if (jsonObj.has("poster_path"))
            posterPath = jsonObj.getString("poster_path")
        if (jsonObj.has("title"))
            title = jsonObj.getString("title")
        if (jsonObj.has("overview"))
        overview = jsonObj.getString("overview")
        if (jsonObj.has("backdrop_path"))
            backdropPath = jsonObj.getString("backdrop_path")
        if (jsonObj.has("vote_average"))
            score = jsonObj.getDouble("vote_average")
    }

    companion object{
        fun getMovies(jsonArray: JSONArray): MutableList<Movie> {
            val movies: MutableList<Movie> = ArrayList<Movie>()
            for (i in 0 until (jsonArray.length()-1)){
                movies.add(Movie(jsonArray.getJSONObject(i)))
            }
            return movies
        }
    }


    public fun getYoutubeId(context: Context){
        val client = AsyncHttpClient()
        val params = RequestParams()
        val apiUrl = "https://api.themoviedb.org/3/movie/" + this.id + "/videos"
        params["api_key"] = ""
        client[apiUrl, params, object :
            JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: okhttp3.Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Movie Video","onFailure")
            }

            override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, json: JSON?) {
                try {
                    val result: JSONArray = json?.jsonObject?.getJSONArray("results") ?: JSONArray()
                    if (result.length() == 0)
                        return
                    videoKey = result.getJSONObject(0).getString("key")

                    val i : Intent = Intent(context, MovieTrailer::class.java).apply{
                        putExtra("key",videoKey)
                    }
                    Log.d("Movie Trailer","start")
                    context.startActivity(i)
                }catch(e: JSONException) {
                    Log.d("Mai n Activity","hit json exception")

                }
            }
        }]
    }
}
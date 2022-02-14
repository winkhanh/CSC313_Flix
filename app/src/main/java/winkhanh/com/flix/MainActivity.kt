package winkhanh.com.flix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.Headers
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import org.json.JSONArray
import org.json.JSONException
import winkhanh.com.flix.adapters.MovieAdapter
import winkhanh.com.flix.models.Movie


class MainActivity : AppCompatActivity() {
    private val apiUrl = "https://api.themoviedb.org/3/movie/now_playing"
    private val apiKey = ""
    var movies : MutableList<Movie> = mutableListOf<Movie>()
    lateinit var adapter : MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Main Activity","startaaa")

        val rvMovies : RecyclerView = findViewById(R.id.rvMovies)
        adapter = MovieAdapter(this, movies)
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = apiKey
        client[apiUrl, params, object :
            JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: okhttp3.Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Main Activity","onFailure")
            }

            override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, json: JSON?) {
                try {
                    val result: JSONArray = json?.jsonObject?.getJSONArray("results") ?: JSONArray()
                    movies.addAll(Movie.getMovies(result))
                    adapter.notifyDataSetChanged()
                }catch(e:JSONException) {
                    Log.d("Main Activity","hit json exception")

                }
            }

        }]

    }
}
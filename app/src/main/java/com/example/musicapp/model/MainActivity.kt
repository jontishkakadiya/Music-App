package com.example.musicapp.model

import com.example.musicapp.adapter.MyRecyclerAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.Interface.ApiInterface
import com.example.musicapp.R
import com.example.musicapp.data.Data
import com.example.musicapp.data.MyData
import com.example.musicapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), MyRecyclerAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        myRecyclerView = findViewById(R.id.songs_recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {

                val dataList = response.body()?.data!!
                myAdapter = MyRecyclerAdapter(dataList)
                binding.songsRecyclerView.adapter = myAdapter
                binding.songsRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2,)
               // binding.songs_recyclerView.adapter = myAdapter
              //  binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                myAdapter.setOnItemClickListener(this@MainActivity)
                Log.d("On Success", "onResponse: " + response.body())

            }

            override fun onFailure(call: Call<MyData?>, response: Throwable) {
                Log.d("On Failure", "onFailure: " + response.message)
            }
        })
    }

    override fun onItemClick(data: Data) {
        val intent = Intent(this, MusicPlayActivity::class.java).apply {
            putExtra("albumCover", data.album.cover)
            putExtra("musicTitle", data.title)
            putExtra("artistName", data.artist.name)
            putExtra("previewUrl", data.preview)
        }
        startActivity(intent)
    }
}


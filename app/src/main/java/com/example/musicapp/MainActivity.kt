package com.example.musicapp

import com.example.musicapp.adapter.MyRecyclerAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.Interface.ApiInterface
import com.example.musicapp.SignUp.SignIn_Activity
import com.example.musicapp.model.Data
import com.example.musicapp.model.MyData
import com.example.musicapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MyRecyclerAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setupRecyclerView()
        fetchData("eminem")

        binding.sidemenu.setOnClickListener {
            popupOptionMenu()
        }
    }

    private fun setupRecyclerView() {
        myAdapter = MyRecyclerAdapter(emptyList())
        binding.songsRecyclerView.apply {
            adapter = myAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        myAdapter.setOnItemClickListener(this)
    }

    private fun popupOptionMenu() {
        val popupMenu = PopupMenu(this, binding.sidemenu)
        val inflater = popupMenu.menuInflater

        inflater.inflate(R.menu.option_menu, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> {
                    logout()
                    true
                }
                R.id.fav_song -> {
                    favoriteSongs()
                    true
                }
                R.id.user_profile -> {
                    userProfile()
                    true
                }
                else -> false
            }
        }
    }

    private fun logout() {
        MyExoPlayer.release()
        startActivity(Intent(this, SignIn_Activity::class.java))
        finish()
    }

    private fun userProfile() {
        startActivity(Intent(this, UserProfileActivity::class.java))
    }

    private fun favoriteSongs() {
        startActivity(Intent(this, FavoriteActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        showPlayerView()
    }

    private fun showPlayerView() {
        MyExoPlayer.getCurrentSong()?.let { song ->
            binding.NowplayingLayout.visibility = View.VISIBLE
            binding.NowplayingTitle.text = song.title
            Glide.with(binding.NowplayingImg)
                .load(song.coverUrl)
                .apply(RequestOptions().transform(RoundedCorners(32)))
                .into(binding.NowplayingImg)
        } ?: run {
            binding.NowplayingLayout.visibility = View.GONE
        }
    }

    private fun fetchData(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val call = retrofit.getData(query)

        call.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                response.body()?.data?.let { dataList ->
                    myAdapter.updateData(dataList)
                    Log.d("MainActivity", "Data loaded successfully: $dataList")
                } ?: run {
                    Log.e("MainActivity", "Response body or data list is null")
                }
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.e("MainActivity", "Failed to load data: ${t.message}")
            }
        })
    }

    override fun onItemClick(data: Data) {
        val intent = Intent(this, PlayerActivity::class.java).apply {
            putExtra("albumCover", data.album.cover)
            putExtra("musicTitle", data.title)
            putExtra("artistName", data.artist.name)
            putExtra("previewUrl", data.preview)
        }
        startActivity(intent)
    }
}


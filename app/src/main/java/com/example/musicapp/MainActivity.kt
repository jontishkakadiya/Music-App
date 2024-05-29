package com.example.musicapp

import com.example.musicapp.adapter.MyRecyclerAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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

        binding.sidemenu.setOnClickListener{
            popupoptionmenu()
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

    private fun popupoptionmenu()
    {
        val popupMenu= PopupMenu(this,binding.sidemenu)
        val inflator = popupMenu.menuInflater

        inflator.inflate(R.menu.option_menu,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.logout->{
                    logout()
                    true
                }
                R.id.fav_song->{
                    FavoriteSongs()
                    true
                }
                R.id.user_profile->{
                    Userprofile()
                    true
                }
                else->false

            }
            false
        }
    }

    private fun logout()
    {
        MyExoPlayer.getInstance()?.release()
       // FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,SignIn_Activity::class.java))
        finish()
    }

    private fun Userprofile()
    {
        startActivity(Intent(this,UserProfileActivity::class.java))
    }
    private fun FavoriteSongs()
    {
        startActivity(Intent(this,FavoriteActivity::class.java))
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
                    // You can show a default message to the user
                }
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.e("MainActivity", "Failed to load data: ${t.message}")
                // Show an error message to the user
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



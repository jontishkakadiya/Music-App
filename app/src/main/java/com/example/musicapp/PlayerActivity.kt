package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.example.musicapp.databinding.ActivityPlayerBinding
import com.example.musicapp.model.Data

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var exoPlayer: ExoPlayer

    @OptIn(UnstableApi::class) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumCover = intent.getStringExtra("albumCover")
        val musicTitle = intent.getStringExtra("musicTitle")
        val artistName = intent.getStringExtra("artistName")
        val previewUrl = intent.getStringExtra("previewUrl")

        // Debugging: Log values received from intent extras
        Log.d("PlayerActivity", "Album Cover: $albumCover")
        Log.d("PlayerActivity", "Music Title: $musicTitle")
        Log.d("PlayerActivity", "Artist Name: $artistName")
        Log.d("PlayerActivity", "Preview URL: $previewUrl")

        if (albumCover != null && musicTitle != null && artistName != null && previewUrl != null) {
            binding.playerTitle.text = musicTitle
            binding.playerSubtitle.text = artistName
            Glide.with(binding.root.context)
                .load(albumCover)
                .circleCrop()
                .into(binding.playerCoverImage)
        } else {
            // Handle the case where intent extras are null
            Log.e("PlayerActivity", "Intent extras are null or incomplete")
            // You can show a default message or finish the activity
            finish()
        }

        exoPlayer = MyExoPlayer.getInstance() ?: return
        binding.plyerview.player = exoPlayer

        binding.PlayerFavBtn.setOnClickListener {
            startActivity(Intent(it.context, FavoriteActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release ExoPlayer instance to prevent memory leaks
        exoPlayer.release()
    }
}


//package com.example.musicapp
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.media3.common.util.UnstableApi
//import androidx.media3.exoplayer.ExoPlayer
//import com.bumptech.glide.Glide
//import com.example.musicapp.databinding.ActivityPlayerBinding
//import com.example.musicapp.model.Data
//
//class PlayerActivity : AppCompatActivity() {
//
//    lateinit var binding: ActivityPlayerBinding
//    private lateinit var exoPlayer: ExoPlayer
//    lateinit var data: Data
//    @OptIn(UnstableApi::class) override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityPlayerBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        MyExoPlayer.getcurrentSong()?.apply {
//            binding.playerTitle.text= data.title
//            binding.playerSubtitle.text= data.artist.name
//            //Glide.with(binding.playerCoverImage)
//            Glide.with(binding.root.context)
//                .load(data.album.cover)
//                .circleCrop()
//                .into(binding.playerCoverImage)
//
//            exoPlayer =MyExoPlayer.getInstance()!!
//            binding.plyerview.player = exoPlayer
//        }
//
//        binding.PlayerFavBtn.setOnClickListener {
//            val currentSong = MyExoPlayer.getcurrentSong()
//            if (currentSong != null) {
//                MyExoPlayer.addToFavorite(binding.root.context,currentSong)
//
//            }
//            // Get the song model for the current ite
//
//            it.context.startActivity(Intent(it.context,FavoriteActivity::class.java))
//
//        }
//
//    }
//}
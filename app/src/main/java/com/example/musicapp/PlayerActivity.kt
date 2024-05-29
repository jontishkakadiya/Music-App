package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.databinding.ActivityPlayerBinding
import com.example.musicapp.model.SongModel

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var exoPlayer: ExoPlayer

    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumCover = intent.getStringExtra("albumCover")
        val musicTitle = intent.getStringExtra("musicTitle")
        val artistName = intent.getStringExtra("artistName")
        val previewUrl = intent.getStringExtra("previewUrl")

        Log.d("PlayerActivity", "Album Cover: $albumCover")
        Log.d("PlayerActivity", "Music Title: $musicTitle")
        Log.d("PlayerActivity", "Artist Name: $artistName")
        Log.d("PlayerActivity", "Preview URL: $previewUrl")

        if (albumCover != null && musicTitle != null && artistName != null && previewUrl != null) {
            binding.playerTitle.text = musicTitle
            binding.playerSubtitle.text = artistName
            Glide.with(binding.root.context)
                .load(albumCover)
                .apply(RequestOptions().transform(RoundedCorners(32)))
                .into(binding.playerCoverImage)

            val song = SongModel(
                id = "",  // You can adjust this based on your needs
                title = musicTitle,
                subtitle = artistName,
                url = previewUrl,
                isfavorite = false,  // Adjust this based on your logic
                coverUrl = albumCover
            )

            MyExoPlayer.startPlaying(this, song)
            exoPlayer = MyExoPlayer.getInstance() ?: run {
                Log.e("PlayerActivity", "Failed to get ExoPlayer instance")
                finish()
                return
            }

            binding.plyerview.player = exoPlayer
        } else {
            Log.e("PlayerActivity", "Intent extras are null or incomplete")
            finish()
        }

        binding.PlayerFavBtn.setOnClickListener {
            startActivity(Intent(it.context, FavoriteActivity::class.java))
        }

        // Add a back button functionality
//        binding.backButton.setOnClickListener {
//            finish()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MyExoPlayer.release()
    }
}

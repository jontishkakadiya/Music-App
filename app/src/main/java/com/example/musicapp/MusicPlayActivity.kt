package com.example.musicapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.databinding.ActivityMusicPlayBinding
import com.squareup.picasso.Picasso

class MusicPlayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicPlayBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumCover = intent.getStringExtra("albumCover")
        val musicTitle = intent.getStringExtra("musicTitle")
        val artistName = intent.getStringExtra("artistName")
        val previewUrl = intent.getStringExtra("previewUrl")

        // Use the data to update UI components
        binding.songTitle.text = musicTitle
        binding.artistName.text = artistName
        // Load album cover using Picasso or any other image loading library
        // For example:
        Picasso.get().load(albumCover).into(binding.SongImage)

        mediaPlayer = MediaPlayer().apply {
            setDataSource(previewUrl)
            prepareAsync()
        }

        // Set click listeners for play and pause buttons
        binding.btnPlay.setOnClickListener {
            mediaPlayer.start()
        }

        binding.btnPause.setOnClickListener {
            mediaPlayer.pause()
        }
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }
}



//package com.example.musicapp
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import com.example.musicapp.databinding.ActivityMusicPlayBinding
//import com.example.musicapp.data.Data
//
//class com.example.musicapp.MusicPlayActivity : AppCompatActivity() {
//    private  lateinit var binding: ActivityMusicPlayBinding
//    private lateinit var data: Data
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        binding = ActivityMusicPlayBinding.inflate(layoutInflater)
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(binding.root)
//
//
//
//    }
//}
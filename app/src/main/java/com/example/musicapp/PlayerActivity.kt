package com.example.musicapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.databinding.ActivityPlayerBinding


class PlayerActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlayerBinding
    lateinit var exoPlayer: ExoPlayer
    @OptIn(UnstableApi::class) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        MyExoPlayer.getcurrentSong()?.apply {
            binding.playerTitle.text= title
            binding.playerSubtitle.text= subtitle
            Glide.with(binding.playerCoverImage).load(coverUrl)
                .circleCrop()
                .into(binding.playerCoverImage)

            exoPlayer =MyExoPlayer.getInstance()!!
            binding.plyerview.player = exoPlayer
        }

        binding.PlayerFavBtn.setOnClickListener {
            val currentSong = MyExoPlayer.getcurrentSong()
            if (currentSong != null) {
                MyExoPlayer.addToFavorite(binding.root.context,currentSong)

            }
            // Get the song model for the current ite

            it.context.startActivity(Intent(it.context,FavoriteActivity::class.java))

        }

    }
}

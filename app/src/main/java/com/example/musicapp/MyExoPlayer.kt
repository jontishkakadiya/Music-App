package com.example.musicapp

import android.content.Context
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicapp.model.SongModel

object MyExoPlayer {

    private var exoPlayer: ExoPlayer? = null
    private var currentSong: SongModel? = null
    private const val TAG = "MyExoPlayer"

    fun getInstance(): ExoPlayer? {
        return exoPlayer
    }

    fun getCurrentSong(): SongModel? {
        return currentSong
    }

    fun startPlaying(context: Context?, song: SongModel) {
        if (context == null) {
            Log.e(TAG, "Context is null, cannot start playing")
            return
        }

        try {
            if (exoPlayer == null) {
                exoPlayer = ExoPlayer.Builder(context).build()
                Log.d(TAG, "ExoPlayer initialized successfully")
            }

            if (currentSong != song) {
                currentSong = song
                currentSong?.url?.let { url ->
                    val mediaItem = MediaItem.fromUri(url)
                    exoPlayer?.setMediaItem(mediaItem)
                    exoPlayer?.prepare()
                    exoPlayer?.play()
                    Log.d(TAG, "Started playing: $url")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing ExoPlayer: ${e.message}")
        }
    }

    fun release() {
        exoPlayer?.release()
        exoPlayer = null
        currentSong = null
        Log.d(TAG, "ExoPlayer released")
    }

}











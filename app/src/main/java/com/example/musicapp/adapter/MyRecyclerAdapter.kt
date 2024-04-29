package com.example.musicapp.adapter

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.MusicPlayActivity
import com.example.musicapp.data.Data
import com.example.musicapp.databinding.RecyclerviewRowBinding
import com.squareup.picasso.Picasso

class MyRecyclerAdapter(private val context: Activity, private val dataList: List<Data>) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: RecyclerviewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data, mediaPlayer: MediaPlayer, context: Activity) {
            binding.apply {
                musicTitle.text = data.title
                artistName.text = data.artist.name
                Picasso.get().load(data.album.cover).into(musicImage)

                // Set click listener to the whole row
                root.setOnClickListener {
                    val intent = Intent(context, MusicPlayActivity::class.java)
                    // Pass any data you need to the next activity using intent extras
                    intent.putExtra("data", data)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRowBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = dataList[position]
        val mediaPlayer = MediaPlayer.create(context, currentData.preview?.toUri())
        holder.bind(currentData, mediaPlayer, context)
    }
}





//package com.example.musicapp.adapter
//
//import android.app.Activity
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.musicapp.MusicPlayActivity
//import com.example.musicapp.data.Data
//import com.example.musicapp.databinding.RecyclerviewRowBinding
//import com.squareup.picasso.Picasso
//
//class MyRecyclerAdapter(private val context: Activity, private val dataList: List<Data>) :
//    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {
//
//    class MyViewHolder(private val binding: RecyclerviewRowBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(data: Data, context: Activity) {
//
//            binding.apply {
//                musicTitle.text = data.title
//                artistName.text = data.artist.name
//                Picasso.get().load(data.album.cover).into(musicImage)
//
//                root.setOnClickListener {
//                    val intent = Intent(context, MusicPlayActivity::class.java)
//                    intent.putExtra("data", data)
//                    context.startActivity(intent)
//                }
//
//            }
//        }
//
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = RecyclerviewRowBinding.inflate(inflater, parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = dataList.size
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentData = dataList[position]
//
//        holder.bind(currentData, context)
//
//
//    }
//}


//package com.example.musicapp.adapter
//
//import android.app.Activity
//import android.media.MediaPlayer
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.core.net.toUri
//import androidx.recyclerview.widget.RecyclerView
//import com.example.musicapp.data.Data
//import com.example.musicapp.databinding.RecyclerviewRowBinding
//import com.squareup.picasso.Picasso
//
//class MyRecyclerAdapter(private val context: Activity, private val dataList: List<Data>) :
//    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {
//
//    class MyViewHolder(private val binding: RecyclerviewRowBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(data: Data, mediaPlayer: MediaPlayer) {
//            binding.apply {
//                musicTitle.text = data.title
//                Picasso.get().load(data.album.cover).into(musicImage)
//                btnMusicPlay.setOnClickListener {
//                    mediaPlayer.start()
//                }
//                btnMusicPause.setOnClickListener {
//                    mediaPlayer.stop()
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = RecyclerviewRowBinding.inflate(inflater, parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = dataList.size
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentData = dataList[position]
//
//        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())
//        holder.bind(currentData, mediaPlayer)
//
//    }
//}

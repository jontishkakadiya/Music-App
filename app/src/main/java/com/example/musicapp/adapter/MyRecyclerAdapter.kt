package com.example.musicapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.data.Data
import com.example.musicapp.databinding.RecyclerviewRowBinding
import com.squareup.picasso.Picasso

class MyRecyclerAdapter(private val dataList: List<Data>) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: RecyclerviewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {
            binding.apply {
                musicTitle.text = data.title
                artistName.text = data.artist.name
                Picasso.get().load(data.album.cover).into(musicImage)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(data: Data)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRowBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = dataList[position]
        holder.bind(currentData)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentData)
        }
    }
}



//package com.example.musicapp.adapter
//
//import android.app.Activity
//import android.content.Intent
//import android.media.MediaPlayer
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.core.net.toUri
//import androidx.recyclerview.widget.RecyclerView
//import com.example.musicapp.model.MusicPlayActivity
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
//        fun bind(data: Data, mediaPlayer: MediaPlayer, context: Activity) {
//            binding.apply {
//                musicTitle.text = data.title
//                artistName.text = data.artist.name
//                Picasso.get().load(data.album.cover).into(musicImage)
//
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
//        val mediaPlayer = MediaPlayer.create(context, currentData.preview?.toUri())
//        holder.bind(currentData, mediaPlayer, context)
//    }
//}

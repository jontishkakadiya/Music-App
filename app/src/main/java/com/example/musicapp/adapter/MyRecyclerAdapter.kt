package com.example.musicapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.databinding.RecyclerviewRowBinding
import com.example.musicapp.model.Data

class MyRecyclerAdapter(private val dataList: List<Data>) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: RecyclerviewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {
            binding.apply {
                ArtistName.text = data.artist.name
                SongTitle.text = data.title

                Glide.with(binding.root.context)
                    .load(data.album.cover)
                    .apply(RequestOptions().transform(RoundedCorners(32)))
                    .into(SongAlbum)
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
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners
//import com.bumptech.glide.request.RequestOptions
//import com.example.musicapp.databinding.RecyclerviewRowBinding
//import com.example.musicapp.model.Data
//
//class MyRecyclerAdapter(private val dataList: List<Data>) :
//    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {
//
//    class MyViewHolder(private val binding: RecyclerviewRowBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(data: Data) {
//            binding.apply {
//                ArtistName.text = data.artist.name
//                SongTitle.text = data.title
//
//                Glide.with(binding.root.context)
//                    .load(data.album.cover)
//                    .apply(RequestOptions().transform(RoundedCorners(32)))
//                    .into(SongAlbum)
//            }
//        }
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(data: Data)
//    }
//
//    private var listener: OnItemClickListener? = null
//
//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        this.listener = listener
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
//        holder.bind(currentData)
//        holder.itemView.setOnClickListener {
//            listener?.onItemClick(currentData)
//        }
//    }
//}
//
//
//
////
////package com.example.musicapp.adapter
////
////import android.view.LayoutInflater
////import android.view.ViewGroup
////import androidx.recyclerview.widget.RecyclerView
////import com.bumptech.glide.Glide
////import com.bumptech.glide.load.resource.bitmap.RoundedCorners
////import com.bumptech.glide.request.RequestOptions
////import com.example.musicapp.model.Data
////import com.example.musicapp.databinding.RecyclerviewRowBinding
////
////class MyRecyclerAdapter(private val dataList: List<Data>) :
////    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {
////
////    class MyViewHolder(private val binding: RecyclerviewRowBinding) :
////        RecyclerView.ViewHolder(binding.root) {
////
////        fun bind(data: Data) {
////            binding.apply {
////                ArtistName.text = data.artist.name
////                SongTitle.text = data.title
////
////                Glide.with(binding.root.context)
////                    .load(data.album.cover)
////                    .apply(RequestOptions().transform(RoundedCorners(32)))
////                    .into(SongAlbum)
////                // Picasso.get().load(data.album.cover).into(SongAlbum)
////            }
////        }
////    }
////
////    interface OnItemClickListener {
////        fun onItemClick(data: Data)
////    }
////
////    private var listener: OnItemClickListener? = null
////
////    fun setOnItemClickListener(listener: OnItemClickListener) {
////        this.listener = listener
////    }
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
////        val inflater = LayoutInflater.from(parent.context)
////        val binding = RecyclerviewRowBinding.inflate(inflater, parent, false)
////        return MyViewHolder(binding)
////    }
////
////    override fun getItemCount(): Int = dataList.size
////
////    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
////        val currentData = dataList[position]
////        holder.bind(currentData)
////        holder.itemView.setOnClickListener {
////            listener?.onItemClick(currentData)
////        }
////    }
////}
////
////

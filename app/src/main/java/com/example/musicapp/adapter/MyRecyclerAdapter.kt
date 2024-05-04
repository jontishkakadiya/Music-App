package com.example.musicapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.data.Data
import com.example.musicapp.databinding.RecyclerviewRowBinding
import com.squareup.picasso.Picasso

class MyRecyclerAdapter(private val context: Activity, private val dataList: List<Data>) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: RecyclerviewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data, context: Activity) {
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
        holder.bind(currentData, context)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentData)
        }
    }
}




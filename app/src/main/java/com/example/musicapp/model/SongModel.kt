package com.example.musicapp.model

data class SongModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val url: String,
    val isfavorite:Boolean,
    val coverUrl: String,
){
    constructor():this("","","","",false,"",)
}
package com.example.musicapp.model

import android.os.Parcel
import android.os.Parcelable

data class Album(
    val cover: String?,
    val cover_big: String?,
    val cover_medium: String?,
    val cover_small: String?,
    val cover_xl: String?,
    val id: Int,
    val md5_image: String?,
    val title: String?,
    val tracklist: String?,
    val type: String?
)
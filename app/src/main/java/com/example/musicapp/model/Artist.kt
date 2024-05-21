package com.example.musicapp.model

import android.os.Parcel
import android.os.Parcelable

data class Artist(
    val id: Int,
    val link: String?,
    val name: String?,
    val picture: String?,
    val picture_big: String?,
    val picture_medium: String?,
    val picture_small: String?,
    val picture_xl: String?,
    val tracklist: String?,
    val type: String?
)
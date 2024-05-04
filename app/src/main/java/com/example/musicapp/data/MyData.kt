package com.example.musicapp.data

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class MyData(
    val data: ArrayList<Data>?,
    val next: String?,
    val total: Int
)
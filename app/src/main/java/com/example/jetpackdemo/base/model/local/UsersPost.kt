package com.example.jetpackdemo.base.model.local

import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersPost(@PrimaryKey val postId: Int,
                     val postTitle: String,
                     val postBody: String,
                     val userName: String) : Parcelable {
}
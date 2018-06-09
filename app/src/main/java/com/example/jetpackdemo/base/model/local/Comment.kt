package com.example.jetpackdemo.base.model.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(entity = Post::class, parentColumns = ["id"],
        childColumns = ["postId"], onDelete = ForeignKey.CASCADE)], indices = [(Index("postId"))])
data class Comment(@SerializedName("postId") val postId: Int,
                   @SerializedName("id") @PrimaryKey val id: Int,
                   @SerializedName("name") val name: String,
                   @SerializedName("email") val email: String,
                   @SerializedName("body") val body: String)
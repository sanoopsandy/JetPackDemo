package com.example.jetpackdemo.base.model.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [(ForeignKey(entity = User::class, parentColumns = ["id"],
        childColumns = ["userId"], onDelete = ForeignKey.CASCADE))],
        indices = [(Index("userId"))])
data class Post(@SerializedName("userId") val userId: Int,
                @SerializedName("id") @PrimaryKey val id: Int,
                @SerializedName("title") val title: String,
                @SerializedName("body") val body: String)
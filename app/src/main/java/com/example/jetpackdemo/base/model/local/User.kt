package com.example.jetpackdemo.base.model.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [(Index("id"))])
data class User(@SerializedName("id") @PrimaryKey val id: Int,
                @SerializedName("name") val name: String,
                @SerializedName("username") val userName: String,
                @SerializedName("email") val email: String)
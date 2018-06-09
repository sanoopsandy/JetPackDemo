package com.example.jetpackdemo.base.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.jetpackdemo.base.model.dao.CommentDao
import com.example.jetpackdemo.base.model.dao.PostDao
import com.example.jetpackdemo.base.model.dao.UserDao
import com.example.jetpackdemo.base.model.local.Comment
import com.example.jetpackdemo.base.model.local.Post
import com.example.jetpackdemo.base.model.local.User

@Database(entities = [Post::class, User::class, Comment::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao

}
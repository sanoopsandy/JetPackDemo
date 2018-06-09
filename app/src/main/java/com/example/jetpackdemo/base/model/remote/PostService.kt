package com.example.jetpackdemo.base.model.remote

import com.example.jetpackdemo.base.model.local.Comment
import com.example.jetpackdemo.base.model.local.Post
import com.example.jetpackdemo.base.model.local.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("/posts/")
    fun getPosts(): Flowable<List<Post>>

    @GET("/users/")
    fun getUsers(): Flowable<List<User>>

    @GET("/comments/")
    fun getComments(@Query("postId") postId: Int): Flowable<List<Comment>>
}
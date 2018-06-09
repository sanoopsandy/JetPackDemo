package com.example.jetpackdemo.list.dataManger

import com.example.jetpackdemo.base.model.local.Post
import com.example.jetpackdemo.base.model.local.User
import com.example.jetpackdemo.base.model.remote.PostService
import io.reactivex.Flowable

class ListRemoteHandler(private val postService: PostService) : ListRepoBluePrint.Remote {
    override fun getUsers(): Flowable<List<User>> {
        return postService.getUsers()
    }

    override fun getPosts(): Flowable<List<Post>> {
        return postService.getPosts()
    }
}
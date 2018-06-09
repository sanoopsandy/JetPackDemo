package com.example.jetpackdemo.list.dataManger

import com.example.jetpackdemo.base.model.local.Post
import com.example.jetpackdemo.base.model.local.User
import com.example.jetpackdemo.base.model.local.UsersPost
import com.example.jetpackdemo.base.networking.DataResult
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

interface ListRepoBluePrint {
    interface Repository {
        val postFetchDataResult: PublishSubject<DataResult<List<UsersPost>>>
        fun fetchPost()
        fun refreshPost()
        fun saveUserAndPosts(user: List<User>, post: List<Post>)
        fun handleError(error: Throwable)
    }

    interface Remote {
        fun getUsers(): Flowable<List<User>>
        fun getPosts(): Flowable<List<Post>>
    }

    interface Local {
        fun getUsersWithPost(): Flowable<List<UsersPost>>
        fun saveUsersAndPost(users: List<User>, posts: List<Post>)
    }

}
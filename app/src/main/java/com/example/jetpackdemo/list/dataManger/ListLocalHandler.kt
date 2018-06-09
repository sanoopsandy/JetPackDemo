package com.example.jetpackdemo.list.dataManger

import com.example.jetpackdemo.base.model.AppDB
import com.example.jetpackdemo.base.model.local.Post
import com.example.jetpackdemo.base.model.local.User
import com.example.jetpackdemo.base.model.local.UsersPost
import com.example.jetpackdemo.doOnBack
import io.reactivex.Completable
import io.reactivex.Flowable

class ListLocalHandler(private val appDB: AppDB) : ListRepoBluePrint.Local {
    override fun getUsersWithPost(): Flowable<List<UsersPost>> {
        return appDB.postDao().getAllPostWithUser()
    }

    override fun saveUsersAndPost(users: List<User>, posts: List<Post>) {
        Completable.fromAction {
            appDB.userDao().insertAll(users)
            appDB.postDao().insertAll(posts)
        }
                .doOnBack()
                .subscribe()
    }
}
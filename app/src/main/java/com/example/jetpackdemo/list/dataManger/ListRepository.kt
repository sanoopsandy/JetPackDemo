package com.example.jetpackdemo.list.dataManger

import com.example.jetpackdemo.*
import com.example.jetpackdemo.base.model.local.Post
import com.example.jetpackdemo.base.model.local.User
import com.example.jetpackdemo.base.model.local.UsersPost
import com.example.jetpackdemo.base.networking.DataResult
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

class ListRepository(private val local: ListRepoBluePrint.Local,
                     private val remote: ListRepoBluePrint.Remote,
                     private val compositeDisposable: CompositeDisposable) : ListRepoBluePrint.Repository {

    override val postFetchDataResult: PublishSubject<DataResult<List<UsersPost>>> = PublishSubject.create<DataResult<List<UsersPost>>>()

    var remoteFetch = true

    override fun fetchPost() {
        postFetchDataResult.loading(true)
        local.getUsersWithPost()
                .doOnBackOutOnMain()
                .subscribe({ userPosts ->
                    postFetchDataResult.success(userPosts)
                    if (remoteFetch)
                        refreshPost()
                    remoteFetch = false
                }, { error -> })
                .addTo(compositeDisposable)

    }

    override fun refreshPost() {
        postFetchDataResult.loading(true)
        Flowable.zip(
                remote.getUsers(),
                remote.getPosts(),
                BiFunction<List<User>, List<Post>, Unit> { users, posts -> saveUserAndPosts(users, posts) }
        )
                .doOnBackOutOnMain()
                .subscribe({}, { error -> handleError(error) })
                .addTo(compositeDisposable)
    }

    override fun saveUserAndPosts(user: List<User>, post: List<Post>) {
        local.saveUsersAndPost(user, post)
    }

    override fun handleError(error: Throwable) {
        postFetchDataResult.failure(error)
    }
}
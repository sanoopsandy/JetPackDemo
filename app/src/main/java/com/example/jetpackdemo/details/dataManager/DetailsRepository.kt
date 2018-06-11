package com.example.jetpackdemo.details.dataManager

import com.example.jetpackdemo.*
import com.example.jetpackdemo.base.model.local.Comment
import com.example.jetpackdemo.base.networking.DataResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class DetailsRepository(private val local: DetailsRepoBluePrint.Local,
                        private val remote: DetailsRepoBluePrint.Remote,
                        private val compositeDisposable: CompositeDisposable) : DetailsRepoBluePrint.Repository {

    var isRemote = true

    override val publishDetailsInfo: PublishSubject<DataResult<List<Comment>>> = PublishSubject.create<DataResult<List<Comment>>>()

    override fun getCommentsForPost(postId: Int) {
        publishDetailsInfo.loading(true)
        local.fetchCommentsForPost(postId)
                .doOnBackOutOnMain()
                .subscribe({ comments ->
                    publishDetailsInfo.success(comments)
                    if (isRemote)
                        refreshComments(postId)
                    isRemote = false
                }, { handleError(it) })
                .addTo(compositeDisposable)

    }

    override fun refreshComments(postId: Int) {
        publishDetailsInfo.loading(true)
        remote.getCommentsForPost(postId)
                .doOnBackOutOnMain()
                .subscribe({ comments ->
                    savePostAndComment(comments)
                }, { handleError(error = it) })
                .addTo(compositeDisposable)
    }

    override fun savePostAndComment(comment: List<Comment>) {
        local.saveCommentsForPost(comment)
    }

    override fun handleError(error: Throwable) {
        error.printStackTrace()
        publishDetailsInfo.failure(error)
    }
}
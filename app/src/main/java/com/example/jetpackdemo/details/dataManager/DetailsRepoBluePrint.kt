package com.example.jetpackdemo.details.dataManager

import com.example.jetpackdemo.base.model.local.Comment
import com.example.jetpackdemo.base.networking.DataResult
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

interface DetailsRepoBluePrint {
    interface Repository {
        val publishDetailsInfo: PublishSubject<DataResult<List<Comment>>>
        fun getCommentsForPost(postId: Int)
        fun refreshComments(postId: Int)
        fun savePostAndComment(comment: List<Comment>)
        fun handleError(error: Throwable)
    }

    interface Local {
        fun fetchCommentsForPost(postId: Int): Flowable<List<Comment>>
        fun saveCommentsForPost(comments: List<Comment>)
    }

    interface Remote {
        fun getCommentsForPost(postId: Int): Flowable<List<Comment>>
    }

}
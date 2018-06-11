package com.example.jetpackdemo.details.dataManager

import com.example.jetpackdemo.base.model.AppDB
import com.example.jetpackdemo.base.model.local.Comment
import com.example.jetpackdemo.doOnBack
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailsLocalHandler(private val appDB: AppDB) : DetailsRepoBluePrint.Local {
    override fun fetchCommentsForPost(postId: Int): Flowable<List<Comment>> {
        return appDB.commentDao().getCommentsForPost(postId)
    }

    override fun saveCommentsForPost(comments: List<Comment>) {
        Completable.fromAction {
            appDB.commentDao().insert(comments)
        }
                .doOnBack()
                .subscribe()
    }
}
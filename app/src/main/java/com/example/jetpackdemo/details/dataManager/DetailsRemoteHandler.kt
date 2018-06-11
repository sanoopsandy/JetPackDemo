package com.example.jetpackdemo.details.dataManager

import com.example.jetpackdemo.base.model.local.Comment
import com.example.jetpackdemo.base.model.remote.PostService
import io.reactivex.Flowable

class DetailsRemoteHandler(private val postService: PostService) : DetailsRepoBluePrint.Remote {
    override fun getCommentsForPost(postId: Int): Flowable<List<Comment>> {
        return postService.getComments(postId)
    }
}
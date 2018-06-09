package com.example.jetpackdemo.base.model.dao

import android.arch.persistence.room.*
import com.example.jetpackdemo.base.model.local.Comment
import io.reactivex.Flowable

@Dao
interface CommentDao {

    @Query("SELECT * FROM Comment WHERE postId = :postId")
    fun getCommentsForPost(postId: Int): Flowable<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comment: Comment)

    @Delete
    fun delete(comment: Comment)
}
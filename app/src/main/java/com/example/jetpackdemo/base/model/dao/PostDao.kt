package com.example.jetpackdemo.base.model.dao

import android.arch.persistence.room.*
import com.example.jetpackdemo.base.model.local.Post
import com.example.jetpackdemo.base.model.local.UsersPost
import io.reactivex.Flowable

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getAll(): Flowable<List<Post>>

    @Delete
    fun delete(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>)

    @Query("SELECT post.id as postId, post.title as postTitle, post.body as postBody, user.userName as userName FROM post, user WHERE post.userId == user.id")
    fun getAllPostWithUser(): Flowable<List<UsersPost>>

}
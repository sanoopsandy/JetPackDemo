package com.example.jetpackdemo.base.model.dao

import android.arch.persistence.room.*
import com.example.jetpackdemo.base.model.local.User
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getALL(): Flowable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: List<User>)

    @Delete
    fun delete(user: User)
}
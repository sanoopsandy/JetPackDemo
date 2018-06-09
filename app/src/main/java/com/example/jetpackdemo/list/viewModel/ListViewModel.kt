package com.example.jetpackdemo.list.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.jetpackdemo.base.model.local.UsersPost
import com.example.jetpackdemo.base.networking.DataResult
import com.example.jetpackdemo.common.DIHandler
import com.example.jetpackdemo.list.dataManger.ListRepository
import com.example.jetpackdemo.toLiveData
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListViewModel : ViewModel() {

    init {
        DIHandler.listComponent().inject(this)
    }

    @Inject
    lateinit var repo: ListRepository

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    val postDataRepository: LiveData<DataResult<List<UsersPost>>> by lazy {
        repo.postFetchDataResult.toLiveData(compositeDisposable)
    }

    fun getPosts() {
        if (postDataRepository.value == null)
            repo.fetchPost()

    }

    fun refreshPosts() {
        repo.refreshPost()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        DIHandler.destroyListComponent()
    }

}
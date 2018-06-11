package com.example.jetpackdemo.details.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.jetpackdemo.base.model.local.Comment
import com.example.jetpackdemo.base.networking.DataResult
import com.example.jetpackdemo.common.DIHandler
import com.example.jetpackdemo.details.dataManager.DetailsRepository
import com.example.jetpackdemo.toLiveData
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel : ViewModel() {

    init {
        DIHandler.detailsComponent().inject(this)
    }

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var repo: DetailsRepository

    val commentDataRepo: LiveData<DataResult<List<Comment>>> by lazy {
        repo.publishDetailsInfo.toLiveData(compositeDisposable)
    }

    fun fetchComments(postId: Int) {
        if (commentDataRepo.value == null)
            repo.getCommentsForPost(postId)
    }

    fun refreshComments(postId: Int) {
        repo.refreshComments(postId)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        DIHandler.destroyDetailsComponent()
    }
}
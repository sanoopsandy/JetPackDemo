package com.example.jetpackdemo.details.di

import com.example.jetpackdemo.base.model.AppDB
import com.example.jetpackdemo.base.model.remote.PostService
import com.example.jetpackdemo.common.adapters.BaseRecyclerAdapter
import com.example.jetpackdemo.details.DetailsActivity
import com.example.jetpackdemo.details.dataManager.DetailsLocalHandler
import com.example.jetpackdemo.details.dataManager.DetailsRemoteHandler
import com.example.jetpackdemo.details.dataManager.DetailsRepoBluePrint
import com.example.jetpackdemo.details.dataManager.DetailsRepository
import com.example.jetpackdemo.details.viewModel.DetailsViewModel
import com.example.jetpackdemo.list.di.ListComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@DetailsScope
@Component(dependencies = [ListComponent::class], modules = [DetailsModule::class])
interface DetailsComponent {
    fun inject(detailsViewModel: DetailsViewModel)
    fun inject(detailsViewModel: DetailsActivity)
}

@Module
class DetailsModule {

    @DetailsScope
    @Provides
    fun getAdapter(): BaseRecyclerAdapter = BaseRecyclerAdapter()

    @DetailsScope
    @Provides
    fun getCompositeDiposable(): CompositeDisposable = CompositeDisposable()

    @DetailsScope
    @Provides
    fun getDetailsRepository(local: DetailsRepoBluePrint.Local,
                             remote: DetailsRepoBluePrint.Remote,
                             compositeDisposable: CompositeDisposable): DetailsRepository = DetailsRepository(local, remote, compositeDisposable)

    @DetailsScope
    @Provides
    fun getLocalHandler(appDB: AppDB): DetailsRepoBluePrint.Local = DetailsLocalHandler(appDB)

    @DetailsScope
    @Provides
    fun getRemoteHandler(postService: PostService): DetailsRepoBluePrint.Remote = DetailsRemoteHandler(postService)

}
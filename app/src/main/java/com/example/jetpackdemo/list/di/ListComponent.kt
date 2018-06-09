package com.example.jetpackdemo.list.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.jetpackdemo.base.di.BaseComponent
import com.example.jetpackdemo.base.model.AppDB
import com.example.jetpackdemo.base.model.remote.PostService
import com.example.jetpackdemo.common.adapters.BaseRecyclerAdapter
import com.example.jetpackdemo.list.ListActivity
import com.example.jetpackdemo.list.dataManger.ListLocalHandler
import com.example.jetpackdemo.list.dataManger.ListRemoteHandler
import com.example.jetpackdemo.list.dataManger.ListRepoBluePrint
import com.example.jetpackdemo.list.dataManger.ListRepository
import com.example.jetpackdemo.list.viewModel.ListViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@ListScope
@Component(dependencies = [BaseComponent::class], modules = [ListModule::class])
interface ListComponent {
    fun appDB(): AppDB
    fun inject(listActivity: ListActivity)
    fun inject(listActivity: ListViewModel)
}

@ListScope
@Module
class ListModule {

    @ListScope
    @Provides
    fun getAdapter() = BaseRecyclerAdapter()

    @ListScope
    @Provides
    fun getListRepo(local: ListRepoBluePrint.Local,
                    remote: ListRepoBluePrint.Remote,
                    compositeDisposable: CompositeDisposable): ListRepository = ListRepository(local, remote, compositeDisposable)

    @ListScope
    @Provides
    fun getLocal(appDB: AppDB): ListRepoBluePrint.Local = ListLocalHandler(appDB)

    @ListScope
    @Provides
    fun getRemote(postService: PostService): ListRepoBluePrint.Remote = ListRemoteHandler(postService)

    @ListScope
    @Provides
    fun getCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    /* Base provides dependencies */
    @ListScope
    @Provides
    fun appDB(context: Context): AppDB = Room.databaseBuilder(context, AppDB::class.java, "app.db").build()

    @ListScope
    @Provides
    fun getPostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)


}
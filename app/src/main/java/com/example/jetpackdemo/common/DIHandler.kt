package com.example.jetpackdemo.common

import com.example.jetpackdemo.MyApplication
import com.example.jetpackdemo.list.di.DaggerListComponent
import com.example.jetpackdemo.list.di.ListComponent
import javax.inject.Singleton


@Singleton
object DIHandler {

    private var listComponent: ListComponent? = null

    fun listComponent(): ListComponent {
        if (listComponent == null) {
            listComponent = DaggerListComponent.builder().baseComponent(MyApplication.baseComponent).build()
        }
        return listComponent as ListComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }
}
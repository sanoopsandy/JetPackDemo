package com.example.jetpackdemo

import android.app.Application
import com.example.jetpackdemo.base.Constants
import com.example.jetpackdemo.base.di.AppModule
import com.example.jetpackdemo.base.di.BaseComponent
import com.example.jetpackdemo.base.di.DaggerBaseComponent
import com.example.jetpackdemo.base.di.NetModule

class MyApplication : Application() {


    companion object {
        lateinit var baseComponent: BaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        baseComponent = DaggerBaseComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Constants.BASE_URL))
                .build()
    }
}
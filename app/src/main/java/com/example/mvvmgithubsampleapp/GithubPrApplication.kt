package com.example.mvvmgithubsampleapp

import android.app.Application
import com.example.mvvmgithubsampleapp.di.AppComponent
import com.example.mvvmgithubsampleapp.di.DaggerAppComponent

class GithubPrApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
    }

    companion object {
        lateinit var component: AppComponent

        fun getAppComponent() : AppComponent {
            return component
        }
    }
}
package com.example.mvvmgithubsampleapp.di

import com.example.mvvmgithubsampleapp.repository.ProjectService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): ProjectService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProjectService::class.java)
    }
}
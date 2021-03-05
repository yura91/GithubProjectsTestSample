package com.example.mvvmgithubsampleapp.di;

import com.example.mvvmgithubsampleapp.repository.ProjectService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class AppModule {
    @Singleton @Provides
    ProjectService provideGithubService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProjectService.class);
    }
}

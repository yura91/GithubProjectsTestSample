package com.example.mvvmgithubsampleapp.di

import com.example.mvvmgithubsampleapp.repository.ProjectsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(projectsRepository: ProjectsRepository)
}
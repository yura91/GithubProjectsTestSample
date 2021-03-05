package com.example.mvvmgithubsampleapp.di;

import com.example.mvvmgithubsampleapp.repository.ProjectsRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
})
public interface AppComponent {

    void inject(ProjectsRepository projectsRepository);
}

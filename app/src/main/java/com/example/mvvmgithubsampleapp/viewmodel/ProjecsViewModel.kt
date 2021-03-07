package com.example.mvvmgithubsampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mvvmgithubsampleapp.factory.ProjectsDataFactory
import com.example.mvvmgithubsampleapp.model.FullReadyItem
import java.util.concurrent.Executors

class ProjecsViewModel : ViewModel() {
    var prLiveData: LiveData<PagedList<FullReadyItem>>
    private val projectsDataFactory: ProjectsDataFactory

    init {
        val executor = Executors.newFixedThreadPool(5)

        projectsDataFactory = ProjectsDataFactory()

        val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        prLiveData = LivePagedListBuilder(projectsDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun searchProjects(text: String) {
        projectsDataFactory.search(text)
        prLiveData.value?.dataSource?.invalidate()
    }
}
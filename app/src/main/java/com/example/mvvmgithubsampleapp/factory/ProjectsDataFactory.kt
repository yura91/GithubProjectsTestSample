package com.example.mvvmgithubsampleapp.factory

import androidx.paging.DataSource
import com.example.mvvmgithubsampleapp.model.FullReadyItem
import com.example.mvvmgithubsampleapp.repository.ProjectsRepository


class ProjectsDataFactory() : DataSource.Factory<Long, FullReadyItem>() {
    var query = ""
    override fun create(): DataSource<Long, FullReadyItem> {
        return ProjectsRepository(query)
    }

    fun search(text: String) {
        query = text
    }
}
package com.example.mvvmgithubsampleapp.factory

import androidx.paging.DataSource
import com.example.mvvmgithubsampleapp.model.Item
import com.example.mvvmgithubsampleapp.repository.ProjectsRepository


class ProjectsDataFactory() : DataSource.Factory<Long, Item>() {
    var query = ""
    override fun create(): DataSource<Long, Item> {
        return ProjectsRepository(query)
    }

    fun search(text: String) {
        query = text
    }
}
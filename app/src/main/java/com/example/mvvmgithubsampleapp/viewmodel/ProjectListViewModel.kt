package com.example.mvvmgithubsampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmgithubsampleapp.model.ProjectsResponse
import com.example.mvvmgithubsampleapp.repository.GithubProjectsRepository
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(val projectRepository: GithubProjectsRepository) : ViewModel() {

    fun getProjects(query: String): LiveData<ProjectsResponse> = projectRepository.getProjectsList(query)

}
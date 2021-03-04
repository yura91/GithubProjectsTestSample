package com.example.mvvmgithubsampleapp.repository

import com.example.mvvmgithubsampleapp.model.ProjectsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories")
    fun getRepositoriesList(@Query("q") query: String?): Call<ProjectsResponse>?
}
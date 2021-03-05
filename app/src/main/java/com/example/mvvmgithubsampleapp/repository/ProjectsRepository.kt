package com.example.mvvmgithubsampleapp.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.mvvmgithubsampleapp.GithubPrApplication
import com.example.mvvmgithubsampleapp.model.Item
import com.example.mvvmgithubsampleapp.model.ProjectsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class ProjectsRepository(val query: String) :
    PageKeyedDataSource<Long, Item>() {

    @Inject
    lateinit var projectService: ProjectService

    init {
        GithubPrApplication.getAppComponent().inject(this)
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Item>
    ) {
        projectService.getRepositoriesList(query, 1, params.requestedLoadSize)?.enqueue(object :
            Callback<ProjectsResponse> {
            override fun onResponse(
                call: Call<ProjectsResponse>,
                response: Response<ProjectsResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()?.items!!, null, 2L)
                }
            }

            override fun onFailure(call: Call<ProjectsResponse?>, t: Throwable) {
                Log.d("TAG", "fvnjdnvljnl")
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {}

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        projectService.getRepositoriesList(query, params.key, params.requestedLoadSize)?.enqueue(
            object : Callback<ProjectsResponse?> {
                override fun onResponse(
                    call: Call<ProjectsResponse?>,
                    response: Response<ProjectsResponse?>
                ) {
                    if (response.isSuccessful) {
                        val nextKey = params.key + 1
                        callback.onResult(response.body()?.items!!, nextKey)
                    }
                }

                override fun onFailure(call: Call<ProjectsResponse?>, t: Throwable) {
                    Log.d("TAG", "fvnjdnvljnl")
                }
            })
    }
}
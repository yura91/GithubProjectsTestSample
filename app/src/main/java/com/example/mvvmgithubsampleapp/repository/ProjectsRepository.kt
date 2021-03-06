package com.example.mvvmgithubsampleapp.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.mvvmgithubsampleapp.GithubPrApplication
import com.example.mvvmgithubsampleapp.model.Item
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject

class ProjectsRepository(val query: String) :
    PageKeyedDataSource<Long, Item>() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    @Inject
    lateinit var projectService: ProjectService

    init {
        GithubPrApplication.getAppComponent().inject(this)
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Item>
    ) {
        scope.launch {
            delay(2000)
            val response = projectService.getRepositoriesList(query, 1, params.requestedLoadSize)
            try {
                if (response.isSuccessful) {
                    val projects = response.body()?.items
                    projects?.let {
                        callback.onResult(projects, null, 2L)
                    }
                } else {
                    Log.d("Error", "Error: ${response.code()}")
                    Log.d("ErrorBody", "ErrorMessage: ${response.message()}")
                }
            } catch (e: HttpException) {
                Log.d("Error", "Error: ${response.code()}")
                Log.d("ErrorBody", "ErrorMessage: ${response.message()}")
            } catch (e: Throwable) {
                Log.d("Error", "Error: ${response.code()}")
                Log.d("ErrorBody", "ErrorMessage: ${response.message()}")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {}

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        scope.launch {
            delay(2000)
            val response = projectService.getRepositoriesList(query, 1, params.requestedLoadSize)
            try {
                if (response.isSuccessful) {
                    val projects = response.body()?.items
                    val nextKey = params.key + 1
                    projects?.let {
                        callback.onResult(projects, nextKey)
                    }
                } else {
                    Log.d("Error", "Error: ${response.code()}")
                }
            } catch (e: HttpException) {
                Log.d("Error", "Error: ${response.code()}")
            } catch (e: Throwable) {
                Log.d("Error", "Error: ${response.code()}")
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}
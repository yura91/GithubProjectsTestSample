package com.example.mvvmgithubsampleapp.repository

import androidx.paging.PageKeyedDataSource
import com.example.mvvmgithubsampleapp.GithubPrApplication
import com.example.mvvmgithubsampleapp.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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
            val response = projectService.getRepositoriesList(query, 1, params.requestedLoadSize)
            try {
                if (response.isSuccessful) {
                    val projects = response.body()?.items
                    callback.onResult(projects!!, null, 2L)
                } else {
//                    toast("Error: ${response.code()}")
                }
            } catch (e: HttpException) {
//                toast("Exception ${e.message}")
            } catch (e: Throwable) {
//                toast("Ooops: Something else went wrong")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {}

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        scope.launch {
            val response = projectService.getRepositoriesList(query, 1, params.requestedLoadSize)
            try {
                if (response.isSuccessful) {
                    val projects = response.body()?.items
                    val nextKey = params.key + 1
                    callback.onResult(projects!!, nextKey)
                } else {
//                    toast("Error: ${response.code()}")
                }
            } catch (e: HttpException) {
//                toast("Exception ${e.message}")
            } catch (e: Throwable) {
//                toast("Ooops: Something else went wrong")
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}
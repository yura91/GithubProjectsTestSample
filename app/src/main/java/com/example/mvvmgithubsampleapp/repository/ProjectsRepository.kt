package com.example.mvvmgithubsampleapp.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.mvvmgithubsampleapp.GithubPrApplication
import com.example.mvvmgithubsampleapp.model.FullReadyItem
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.text.ParseException
import java.text.SimpleDateFormat
import javax.inject.Inject


class ProjectsRepository(val query: String) :
    PageKeyedDataSource<Long, FullReadyItem>() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    @Inject
    lateinit var projectService: ProjectService

    init {
        GithubPrApplication.getAppComponent().inject(this)
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, FullReadyItem>
    ) {
        scope.launch {
            delay(2000)
            val response = projectService.getRepositoriesList(query, 1, params.requestedLoadSize)
            try {
                if (response.isSuccessful) {
                    val projects = response.body()?.items
                    val converted = projects?.map {
                        val fullReadyItem = FullReadyItem()
                        try {
                            val date =
                                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(it.updatedAt)
                            val formattedDate = SimpleDateFormat("dd MM yyyy").format(date)
                            fullReadyItem.updatedAt = formattedDate
                        } catch (e: ParseException) {
                            //Handle exception here
                            e.printStackTrace()
                        }
                        fullReadyItem.id = it.id
                        fullReadyItem.name = it.name
                        fullReadyItem.description = it.description
                        fullReadyItem.usedLanguages = it.language
                        fullReadyItem.stargazersCount = it.stargazersCount.toString()
                        fullReadyItem.owner.nickName = it.owner?.login
                        fullReadyItem.owner.avatarUrl = it.owner?.avatarUrl
                        fullReadyItem
                    }


                    converted?.let {
                        callback.onResult(it, null, 2L)
                    }
                } else {
                    Log.d("Error", "Error: ${response.code()}")
                    Log.d("ErrorBody", "ErrorMessage: ${response.message()}")
                }
            } catch (e: HttpException) {
                Log.d("Error", "Error: ${response.code()}")
                Log.d("ErrorBody", "ErrorMessage: ${response.message()}")
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, FullReadyItem>
    ) {
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, FullReadyItem>) {
        scope.launch {
            delay(2000)
            val response = projectService.getRepositoriesList(query, 1, params.requestedLoadSize)
            try {
                if (response.isSuccessful) {
                    val projects = response.body()?.items

                    val converted = projects?.map {
                        val fullReadyItem = FullReadyItem()
                        try {
                            val date =
                                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(it.updatedAt)
                            val formattedDate = SimpleDateFormat("dd MM yyyy").format(date)
                            fullReadyItem.updatedAt = formattedDate
                        } catch (e: ParseException) {
                            //Handle exception here
                            e.printStackTrace()
                        }
                        fullReadyItem.id = it.id
                        fullReadyItem.name = it.name
                        fullReadyItem.description = it.description
                        fullReadyItem.usedLanguages = it.language
                        fullReadyItem.stargazersCount = it.stargazersCount.toString()
                        fullReadyItem.owner.nickName = it.owner?.login
                        fullReadyItem.owner.avatarUrl = it.owner?.avatarUrl
                        fullReadyItem
                    }

                    val nextKey = params.key + 1

                    converted?.let {
                        callback.onResult(it, nextKey)
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
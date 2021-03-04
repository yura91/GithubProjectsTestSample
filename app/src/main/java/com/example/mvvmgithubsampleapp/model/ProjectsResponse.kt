package com.example.mvvmgithubsampleapp.model

class ProjectsResponse {
    var total_count : Int = 0
    var incomplete_results : Boolean = false
    var items : List<Item> = listOf()
}
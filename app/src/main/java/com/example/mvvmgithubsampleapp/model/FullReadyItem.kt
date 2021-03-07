package com.example.mvvmgithubsampleapp.model

class FullReadyItem {
    var id: Int? = null
    var name: String? = null
    var description: String? = null
    var usedLanguages: String? = ""
    var updatedAt: String? = null
    var stargazersCount: String? = null
    var owner: FullReadyOwner = FullReadyOwner()
}
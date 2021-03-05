package com.example.mvvmgithubsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmgithubsampleapp.databinding.ActivityMainBinding
import com.example.mvvmgithubsampleapp.view.GitHubListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.container, GitHubListFragment()).commit()
    }
}
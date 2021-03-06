package com.example.mvvmgithubsampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmgithubsampleapp.databinding.ActivityMainBinding
import com.example.mvvmgithubsampleapp.view.ProjectsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.container, ProjectsListFragment())
            beginTransaction.addToBackStack("ProjectsListFragment")
            beginTransaction.commit()
        }
    }
}
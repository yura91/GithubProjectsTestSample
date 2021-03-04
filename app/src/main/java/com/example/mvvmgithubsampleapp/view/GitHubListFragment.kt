package com.example.mvvmgithubsampleapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmgithubsampleapp.GithubPrApplication
import com.example.mvvmgithubsampleapp.databinding.FragmentGithubListBinding
import com.example.mvvmgithubsampleapp.model.ProjectsResponse
import com.example.mvvmgithubsampleapp.viewmodel.ProjectListViewModel
import javax.inject.Inject

class GitHubListFragment : Fragment() {

    @Inject
    lateinit var prListViewModel : ProjectListViewModel

    lateinit var binding: FragmentGithubListBinding
    init {
        GithubPrApplication.getAppComponent().inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentGithubListBinding.bind(view)

        binding.textSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }

}
package com.example.mvvmgithubsampleapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mvvmgithubsampleapp.GithubPrApplication
import com.example.mvvmgithubsampleapp.adapters.ProjectsListAdapter
import com.example.mvvmgithubsampleapp.databinding.FragmentGithubListBinding
import com.example.mvvmgithubsampleapp.viewmodel.ProjecsViewModel
import javax.inject.Inject

class GitHubListFragment : Fragment() {

    private val projectsListAdapter = ProjectsListAdapter()

    private val prListViewModel: ProjecsViewModel by activityViewModels<ProjecsViewModel> ()

    lateinit var binding: FragmentGithubListBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGithubListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.list.adapter = projectsListAdapter

        prListViewModel.prLiveData.observe(viewLifecycleOwner, Observer {
            projectsListAdapter.submitList(it)
            Log.d("tag", "vnlsnlknlkvns")
        })

        binding.textSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                prListViewModel.searchProjects(s.toString())
            }

        })

    }

}
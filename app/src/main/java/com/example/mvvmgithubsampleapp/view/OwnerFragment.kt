package com.example.mvvmgithubsampleapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mvvmgithubsampleapp.databinding.FragmentOwnerBinding
import com.example.mvvmgithubsampleapp.model.Owner

class OwnerFragment : Fragment() {
    lateinit var binding: FragmentOwnerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOwnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val owner: Owner? = arguments?.getParcelable("owner") as Owner?
        binding.nickname.text = owner?.login
        Glide.with(binding.root.context).load(owner?.avatarUrl).into(binding.avatar)
        binding.followingsNumber.text = owner?.followingUrl

    }
}
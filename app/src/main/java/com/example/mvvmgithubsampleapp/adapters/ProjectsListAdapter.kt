package com.example.mvvmgithubsampleapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmgithubsampleapp.DiffUtilCallBack
import com.example.mvvmgithubsampleapp.MainActivity
import com.example.mvvmgithubsampleapp.R
import com.example.mvvmgithubsampleapp.databinding.ProjectListItemBinding
import com.example.mvvmgithubsampleapp.model.FullReadyItem
import com.example.mvvmgithubsampleapp.view.OwnerFragment

class ProjectsListAdapter : PagedListAdapter<FullReadyItem, ProjectsListAdapter.MyViewHolder>(
    DiffUtilCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ProjectListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
        holder.itemView.setOnClickListener {
            val owner = Bundle()
            owner.putParcelable("owner", getItem(position)?.owner)
            val ownerFragment = OwnerFragment()
            ownerFragment.arguments = owner
            val beginTransaction =
                (it.context as MainActivity).supportFragmentManager.beginTransaction()
            beginTransaction.replace(
                R.id.container, ownerFragment
            )
            beginTransaction.addToBackStack("OwnerFragment")
            beginTransaction.commit()
        }
    }

    class MyViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameOfRepo = binding.nameOfRepo
        val desc = binding.description
        val lastModText = binding.lastModDate
        val numberOfStars = binding.numberStars
        val language = binding.usedLanguages
        val ownerAvatar = binding.ownerAvatar

        fun bindPost(item: FullReadyItem) {
            nameOfRepo.text = item.name
            desc.text = item.description
            lastModText.text = item.updatedAt
            language.text = item.usedLanguages
            numberOfStars.text = item.stargazersCount.toString()

            Glide.with(binding.root.context).load(item.owner?.avatarUrl).into(ownerAvatar)
        }
    }
}
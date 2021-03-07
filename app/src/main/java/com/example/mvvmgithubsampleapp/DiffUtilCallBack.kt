package com.example.mvvmgithubsampleapp

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmgithubsampleapp.model.FullReadyItem

class DiffUtilCallBack : DiffUtil.ItemCallback<FullReadyItem>() {
    override fun areItemsTheSame(oldItem: FullReadyItem, newItem: FullReadyItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FullReadyItem, newItem: FullReadyItem): Boolean {
        return oldItem.name == newItem.name
                && oldItem.description == newItem.description
    }

}
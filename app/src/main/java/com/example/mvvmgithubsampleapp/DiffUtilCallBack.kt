package com.example.mvvmgithubsampleapp

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmgithubsampleapp.model.Item

class DiffUtilCallBack : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.name == newItem.name
                && oldItem.fullName == newItem.fullName
                && oldItem.description == newItem.description
    }

}
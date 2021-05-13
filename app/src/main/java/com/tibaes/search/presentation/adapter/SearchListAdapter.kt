package com.tibaes.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tibaes.search.R
import com.tibaes.search.databinding.ItemSearchBinding
import com.tibaes.search.domain.entity.Item

class SearchListAdapter(
    private val onItemClicked: ItemDetailListener?
) : ListAdapter<Item, SearchListAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList.getOrNull(position)?.let {
            holder.bind(it)
        }
    }


    inner class ViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            //  binding.loginOwner.text = item.owner.login
            binding.nameProject.text = item.name
            // binding.watcherNumber.text = item.watchers.toString()
            binding.itemComponent.setOnClickListener {
                onItemClicked?.let{
                    it.navToItemDetails(item)
                }
            }
            binding.executePendingBindings()
        }
    }

}

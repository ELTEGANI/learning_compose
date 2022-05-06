package com.example.test.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.data.Items
import com.example.test.databinding.ItemListBinding


/*
* what adapter provides for RecyclerView
* 1-how many items
* 2-create new viewHolder
* 3-Draw items into viewHolder
* Recyclerview reuse viewHolders(all operations done in it)
*
*
* ListAdapter keep track of list adapter using submitList*/
class ItemAdapter():  ListAdapter<Items, ItemAdapter.ViewHolder>(ItemDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        //This method calls {@link #onCreateViewHolder(ViewGroup, int)} to create a new
        //ViewHolder and initializes some private fields to be used by RecyclerView.
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position:Int){
        //Called by RecyclerView to display the data at the specified position.
        holder.bind(getItem(position))
    }


    class ViewHolder private constructor(private var itemListBinding: ItemListBinding):
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(
            items: Items) {
            itemListBinding.items = items
            itemListBinding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}


//let recyclerview to update only items that were changed its more efficient than drawing the entire list
class ItemDiffCallback: DiffUtil.ItemCallback<Items>(){
    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem.name == newItem.name
    }
    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem == newItem
    }
}


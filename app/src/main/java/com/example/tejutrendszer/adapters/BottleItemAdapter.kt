package com.example.tejutrendszer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tejutrendszer.R
import com.example.tejutrendszer.data.Bottle
import kotlinx.android.synthetic.main.bottles_item.view.*
import kotlinx.android.synthetic.main.customer_item.view.*

class BottleItemAdapter(private val bottleItemList: List<Bottle>): RecyclerView.Adapter<BottleItemAdapter.BottleItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottleItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bottles_item, parent, false)

        return BottleItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BottleItemViewHolder, position: Int) {
        val currentItem = bottleItemList[position]

        holder.bottleName.text = currentItem.bottleName
        holder.bottleCount.text = currentItem.bottleCount
    }

    override fun getItemCount() = bottleItemList.size

    class BottleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bottleName: TextView = itemView.bottle_name
        val bottleCount: TextView = itemView.bottle_count
    }

}
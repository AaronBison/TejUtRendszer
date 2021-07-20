package com.example.tejutrendszer.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tejutrendszer.R
import kotlinx.android.synthetic.main.customer_item.view.*

class CustomerItemAdapter(private val CustomerItemList: List<CustomerItem>) : RecyclerView.Adapter<CustomerItemAdapter.CustomerItemViewHolder>() {

    class CustomerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val customerName: TextView = itemView.customer_name
         val customerDebt: TextView = itemView.customer_debt
         val customerLiter: TextView = itemView.customer_liter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.customer_item, parent, false)

        return CustomerItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomerItemViewHolder, position: Int) {
        val currentItem = CustomerItemList[position]

        holder.customerName.text = currentItem.customerName
        holder.customerDebt.text = currentItem.customerDebt
        holder.customerLiter.text = currentItem.customerLiter
    }

    override fun getItemCount() = CustomerItemList.size
}
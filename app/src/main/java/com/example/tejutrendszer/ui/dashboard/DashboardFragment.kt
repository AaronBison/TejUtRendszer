package com.example.tejutrendszer.ui.dashboard

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tejutrendszer.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private var customerList : List<CustomerItem> = listOf()
    private val sdf = SimpleDateFormat("yyyy MMMM dd (EEEE)")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //generates dummy data for CustomerItem list
        customerList = generateDummyList(500)

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        date_indicator.text = sdf.format(Date()).toString()


        recycler_view.hasFixedSize()
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = CustomerItemAdapter(customerList)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun generateDummyList(size: Int): List<CustomerItem> {
        val list = ArrayList<CustomerItem>()

        for (i in 1 until size){
            val item = CustomerItem("Name $i", "130 lej", "2 l")
            list += item
        }
        return list
    }

}
package com.example.tejutrendszer.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tejutrendszer.R
import com.example.tejutrendszer.ui.adapters.CustomerItemAdapter
import com.example.tejutrendszer.ui.models.CustomerItem
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DashboardFragment : Fragment() {

    private var customerList : List<CustomerItem> = listOf()
    private val generalDateFormat = SimpleDateFormat("yyyy MMMM dd (EEEE)")
    private val todayDateFormat = SimpleDateFormat("dd")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //generates dummy data for CustomerItem list
        customerList = readCustomersFromCSV()

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        date_indicator.text = generalDateFormat.format(Date()).toString()

        if(customerList.isNotEmpty()){
            recycler_view.hasFixedSize()
            recycler_view.layoutManager = LinearLayoutManager(context)
            recycler_view.itemAnimator = DefaultItemAnimator()
            recycler_view.adapter = CustomerItemAdapter(customerList)
        }else{
            text_empty_rv.isVisible = true
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun readCustomersFromCSV(): List<CustomerItem>{
        val listOfCustomers: MutableList<CustomerItem> = mutableListOf()

        // Open input file
        val inputStream = resources.openRawResource(R.raw.customers)
        val reader = BufferedReader(inputStream.bufferedReader())
        val iterator = reader.lineSequence().iterator()

        while (iterator.hasNext()){
            val currentLine = iterator.next()

            // Split by ","
            val tokens = currentLine.split("*").toTypedArray()

            val today = todayDateFormat.format(Date()).toInt()

            Log.w("today.toString()",today.toString())
            Log.w("tokens[today]",tokens[today])

            if(tokens[today] != ""){
                val customerName = tokens[0]
                val customerLiter = tokens[today]
                val customerLiterThisMonth = tokens[tokens.size-2]
                val customerDept = tokens[tokens.size-1]


                val customer = CustomerItem(customerName,customerDept,customerLiter)
                Log.e("WTF",listOfCustomers.toString())
                listOfCustomers.add(customer)
            }
        }


        Log.w("TAG", listOfCustomers.toString())
        reader.close()

        return listOfCustomers
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
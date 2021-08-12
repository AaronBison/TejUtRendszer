package com.example.tejutrendszer.ui.views

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
import com.example.tejutrendszer.adapters.CustomerItemAdapter
import com.example.tejutrendszer.data.Customer
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DashboardFragment : Fragment() {

    private var customerList : List<Customer> = listOf()
    private val generalDateFormat = SimpleDateFormat("yyyy MMMM dd (EEEE)")
    private val todayDateFormat = SimpleDateFormat("dd")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        customerList = readCustomersFromCSV()

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        date_indicator.text = generalDateFormat.format(Date()).toString()

        if(customerList.isNotEmpty()){
            recycler_view_dashboard.hasFixedSize()
            recycler_view_dashboard.layoutManager = LinearLayoutManager(context)
            recycler_view_dashboard.itemAnimator = DefaultItemAnimator()
            recycler_view_dashboard.adapter = CustomerItemAdapter(customerList)
        }else{
            text_empty_rv.isVisible = true
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun readCustomersFromCSV(): List<Customer>{
        val listOfCustomers: MutableList<Customer> = mutableListOf()

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


                val customer = Customer(customerName,customerDept,customerLiter)
                Log.e("WTF",listOfCustomers.toString())
                listOfCustomers.add(customer)
            }
        }


        Log.w("TAG", listOfCustomers.toString())
        reader.close()

        return listOfCustomers
    }

    private fun generateDummyList(size: Int): List<Customer> {
        val list = ArrayList<Customer>()

        for (i in 1 until size){
            val item = Customer("Name $i", "130 lej", "2 l")
            list += item
        }
        return list
    }

}
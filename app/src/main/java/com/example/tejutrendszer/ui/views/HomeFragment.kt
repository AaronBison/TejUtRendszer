package com.example.tejutrendszer.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tejutrendszer.R
import com.example.tejutrendszer.adapters.BottleItemAdapter
import com.example.tejutrendszer.data.Bottle
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.BufferedReader
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var bottleList : List<Bottle> = listOf()
    private val todayDateFormat = SimpleDateFormat("dd")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bottleList = readBottlesFromCSV()

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recycler_view_home.hasFixedSize()
        recycler_view_home.layoutManager = LinearLayoutManager(context)
        recycler_view_home.itemAnimator = DefaultItemAnimator()
        recycler_view_home.adapter = BottleItemAdapter(bottleList)


        super.onViewCreated(view, savedInstanceState)
    }


    private fun readBottlesFromCSV(): List<Bottle>{
        val listOfBottles: MutableList<Bottle> = mutableListOf()

        // Open input file
        val inputStream = resources.openRawResource(R.raw.summary)
        val reader = BufferedReader(inputStream.bufferedReader())
        val iterator = reader.lineSequence().iterator()
        val today = todayDateFormat.format(Date()).toInt()

        while (iterator.hasNext()){
            val currentLine = iterator.next()

            // Split by ","
            val tokens = currentLine.split("*").toTypedArray()

            if (tokens[today] != ""){
                listOfBottles.add(Bottle(tokens[0],tokens[today]))
            }else{
                listOfBottles.add(Bottle(tokens[0],"0"))
            }
        }

        return listOfBottles
    }
}
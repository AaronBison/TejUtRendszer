package com.example.tejutrendszer.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CustomerDao {
    private val customerList = mutableListOf<Customer>()
    private val customers = MutableLiveData<List<Customer>>()

    init {
        customers.value = customerList
    }

    fun addCustomer(customer: Customer){
        customerList.add(customer)
        customers.value = customerList
    }

    fun getCustomers() = customers as LiveData<List<Customer>>
}
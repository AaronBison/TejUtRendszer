package com.example.tejutrendszer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tejutrendszer.data.Customer
import com.example.tejutrendszer.data.CustomerRepository

class DashboardViewModel(private val customerRepository: CustomerRepository) : ViewModel() {

    fun getCustomers() = customerRepository.getCustomer()

    fun addCustomers(customer: Customer) = customerRepository.addCustomer(customer)

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}
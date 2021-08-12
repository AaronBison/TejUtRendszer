package com.example.tejutrendszer.data

class CustomerRepository private constructor(private val customerDao: CustomerDao){

    fun addCustomer(customer: Customer){
        customerDao.addCustomer(customer)
    }

    fun getCustomer() = customerDao.getCustomers()


    companion object{
        @Volatile private var instance: CustomerRepository ?= null

        fun getInstance(customerDao: CustomerDao) =
            instance ?: synchronized(this){
                instance ?: CustomerRepository(customerDao).also { instance = it }
            }
    }
}
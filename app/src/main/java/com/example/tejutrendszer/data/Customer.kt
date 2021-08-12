package com.example.tejutrendszer.data

data class Customer(val customerName: String, val customerDebt: String, val customerLiter: String) {

    override fun toString(): String {
        return "$customerName - $customerDebt lei - $customerLiter l"
    }

}
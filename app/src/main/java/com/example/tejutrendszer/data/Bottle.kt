package com.example.tejutrendszer.data

data class Bottle(val bottleName: String, val bottleCount: String) {

    override fun toString(): String {
        return "$bottleName - $bottleCount db"
    }
}
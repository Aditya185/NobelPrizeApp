package com.adityaprakash.nobelprizeapp.data.vo

data class Prizes(
    val prizes: List<Prize>
) {
    fun get(i: Int): Prize {
           return prizes[i]
    }
}
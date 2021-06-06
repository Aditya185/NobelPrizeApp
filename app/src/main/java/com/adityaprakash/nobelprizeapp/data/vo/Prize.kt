package com.adityaprakash.nobelprizeapp.data.vo

data class Prize(
    val category: String,
    val laureates: List<Laureate>,
    val year: String
)
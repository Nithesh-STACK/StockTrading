package com.example.stocktrading

data class StockData(
    val id: Int = 0,
    val url: String = "",
    val name: String = "",
    val price: Int = 0,
    val description: String = "",
    val owningId:Int?=0,

    )

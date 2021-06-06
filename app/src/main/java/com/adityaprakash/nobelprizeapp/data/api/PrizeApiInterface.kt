package com.adityaprakash.nobelprizeapp.data.api


import com.adityaprakash.nobelprizeapp.data.vo.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PrizeApiInterface {
    @GET("prize.json")
    fun getPrizeData(): Single<Prizes>

}
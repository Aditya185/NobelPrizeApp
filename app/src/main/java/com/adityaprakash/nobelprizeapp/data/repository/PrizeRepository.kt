package com.adityaprakash.nobelprizeapp.data.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.adityaprakash.nobelprizeapp.data.api.PrizeApiInterface
import com.adityaprakash.nobelprizeapp.data.vo.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PrizeRepository(private val apiService: PrizeApiInterface, private  val compositeDisposable: CompositeDisposable) {

    private val _downloadPrizeData =  MutableLiveData<Prizes>()
    val downloadPrizeData: LiveData<Prizes>
        get() = _downloadPrizeData

    fun fetchPrizeData() {



        try {
            compositeDisposable.add(
                apiService.getPrizeData()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadPrizeData.postValue(it)


                        },
                        {

                            Log.e("Prize data", it.message)
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("prize_data",e.message)
        }


    }


}
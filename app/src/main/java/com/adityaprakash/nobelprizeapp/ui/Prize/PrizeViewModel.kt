package com.adityaprakash.nobelprizeapp.ui.Prize


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.adityaprakash.nobelprizeapp.data.vo.Prize
import com.adityaprakash.nobelprizeapp.data.vo.Prizes

import io.reactivex.disposables.CompositeDisposable

class PrizeViewModel(private val prizeDataRepository: PrizeDataRepository)  : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  prizeData : LiveData<Prizes> by lazy {
        prizeDataRepository.fetchPrizeData(compositeDisposable)
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
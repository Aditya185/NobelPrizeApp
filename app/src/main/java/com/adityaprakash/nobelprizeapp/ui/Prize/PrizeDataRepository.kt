package com.adityaprakash.nobelprizeapp.ui.Prize


import androidx.lifecycle.LiveData
import com.adityaprakash.nobelprizeapp.data.api.PrizeApiInterface
import com.adityaprakash.nobelprizeapp.data.repository.PrizeRepository
import com.adityaprakash.nobelprizeapp.data.vo.Prizes
import io.reactivex.disposables.CompositeDisposable

class PrizeDataRepository (private val apiService : PrizeApiInterface) {
    lateinit var prizeRepository: PrizeRepository

    fun fetchPrizeData (compositeDisposable: CompositeDisposable) : LiveData<Prizes> {

        prizeRepository = PrizeRepository(apiService,compositeDisposable)
        prizeRepository.fetchPrizeData()

        return prizeRepository.downloadPrizeData

    }


}
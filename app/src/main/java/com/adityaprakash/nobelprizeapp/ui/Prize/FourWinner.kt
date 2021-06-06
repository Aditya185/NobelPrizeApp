package com.adityaprakash.nobelprizeapp.ui.Prize
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adityaprakash.nobelprizeapp.R

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityaprakash.nobelprizeapp.adapters.FourWinnerAdapter
import com.adityaprakash.nobelprizeapp.data.api.PrizeApiInterface
import com.adityaprakash.nobelprizeapp.data.api.PrizeApiObject
import com.adityaprakash.nobelprizeapp.data.vo.Laureate
import com.adityaprakash.nobelprizeapp.data.vo.Prize
import com.adityaprakash.nobelprizeapp.databinding.FragmentFourWinnerBinding


class FourWinner : Fragment() {

    private lateinit var binding: FragmentFourWinnerBinding
    private lateinit var viewModel: PrizeViewModel
    private lateinit var prizeDataRepository: PrizeDataRepository
    private lateinit var laureateAdapter: FourWinnerAdapter


    private lateinit var prizeList: List<Prize>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Nobel Prize App"
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_four_winner, container, false)



        val apiService : PrizeApiInterface = PrizeApiObject.getClient()
        prizeDataRepository = PrizeDataRepository(apiService)

        viewModel = getViewModel()

        binding.continentRecycler.layoutManager = LinearLayoutManager(context)

        val finalLaureate: ArrayList<Laureate> = arrayListOf()
        var laureateList: HashMap<String, Laureate> = HashMap<String, Laureate>()
        viewModel.prizeData.observe(viewLifecycleOwner, Observer {
            var finalPrizeList: List<Prize> = it.prizes.filter { s -> s.year.toInt() >=1900 &&   s.year.toInt() <=2018 }
            for(prze in finalPrizeList){
                if(prze.laureates != null) {
                    for (l in prze.laureates) {
                        if (laureateList.containsKey(l.id)) {
                            if(l.surname != null){
                                var str:String = laureateList[l.id]!!.motivation
                                l.motivation =str+"Motivation:- "+ l.motivation +"\n Year-"+prze.year+"\n\n"
                                finalLaureate.add(l)
                            }

                        } else {
                            l.motivation ="Motivation:- "+ l.motivation +"\n Year-"+prze.year+"\n\n"
                            laureateList.put(l.id, l)
                        }

                    }
                }
            }
            laureateAdapter = FourWinnerAdapter(finalLaureate,"Category","Year")
            binding.continentRecycler.adapter = laureateAdapter
            laureateAdapter.notifyDataSetChanged()



        })




            //bindUI(it)





//        viewModel.networkState.observe(viewLifecycleOwner, Observer {
//            binding.textContinent.text = if (it == NetworkState.LOADING) "Loading" else ""
//            binding.textContinent.text = if (it == NetworkState.ERROR) "Couldn't fetched the data" else ""
//
//        })

        return binding.root
    }

//    fun bindUI( it: List<ContinentData>){
//        var str:String = ""
//        for(item in it){
//            str+=item.cases
//            str+=item.active
//            str+=item.continent
//        }
//        binding.textContinent.text =str
//        Log.d("hello",str)
//    }


    private fun getViewModel(): PrizeViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return PrizeViewModel(prizeDataRepository) as T
            }
        })[PrizeViewModel::class.java]
    }
}
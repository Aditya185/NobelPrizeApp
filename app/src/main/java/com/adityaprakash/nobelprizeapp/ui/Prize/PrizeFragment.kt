package com.adityaprakash.nobelprizeapp.ui.Prize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adityaprakash.nobelprizeapp.R

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityaprakash.nobelprizeapp.adapters.PrizeAdapter
import com.adityaprakash.nobelprizeapp.data.api.PrizeApiInterface
import com.adityaprakash.nobelprizeapp.data.api.PrizeApiObject
import com.adityaprakash.nobelprizeapp.data.vo.Prize
import com.adityaprakash.nobelprizeapp.databinding.FragmentPrizeBinding


class PrizeFragment : Fragment() {

    private lateinit var binding: FragmentPrizeBinding
    private lateinit var viewModel: PrizeViewModel
    private lateinit var prizeDataRepository: PrizeDataRepository
    private lateinit var prizeAdapter: PrizeAdapter
    private lateinit var categoryAdapter: ArrayAdapter<String>
    private lateinit var yearAdapter: ArrayAdapter<String>

    private lateinit var prizeList: List<Prize>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Nobel Prize App"
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_prize, container, false)



        val apiService : PrizeApiInterface = PrizeApiObject.getClient()
        prizeDataRepository = PrizeDataRepository(apiService)
        val categoryList: ArrayList<String> = arrayListOf("Category")
        val yearList: ArrayList<String> = arrayListOf("Year")
        viewModel = getViewModel()

        binding.continentRecycler.layoutManager = LinearLayoutManager(context)


        viewModel.prizeData.observe(viewLifecycleOwner, Observer {
            var finalPrizeList: List<Prize> = it.prizes.filter { s -> s.year.toInt() >=1900 &&   s.year.toInt() <=2018 }
            prizeAdapter = PrizeAdapter(finalPrizeList,"Category","Year")
            binding.continentRecycler.adapter = prizeAdapter
            prizeAdapter.notifyDataSetChanged()
            prizeList = finalPrizeList
            for(prize in prizeList){
                if(!categoryList.contains(prize.category)){
                    categoryList.add(prize.category)
                }

            }
            categoryAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, categoryList)

            binding.catSpin.adapter= categoryAdapter
            categoryAdapter.notifyDataSetChanged()

            for(i in 1900..2018){
                yearList.add(i.toString())
            }
            var yearWisePrizeList: List<Prize> = finalPrizeList
            var catWisePrizeList: List<Prize> = finalPrizeList
            var category : String = "Category"
            var year : String = "year"

            binding.catSpin.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    category = categoryList[p2]
                    if(category == "Category" && year != "Year") {
                        catWisePrizeList = prizeList.filter { s -> s.year == year }
                        prizeAdapter = PrizeAdapter(catWisePrizeList, "Category", "Year")
                        binding.continentRecycler.adapter = prizeAdapter
                        prizeAdapter.notifyDataSetChanged()
                    }else if(category == "Category" && year == "Year") {
                        prizeAdapter = PrizeAdapter(finalPrizeList, "Category", "Year")
                        binding.continentRecycler.adapter = prizeAdapter
                        prizeAdapter.notifyDataSetChanged()
                    }else if(category != "Category" && year == "Year") {
                        catWisePrizeList = prizeList.filter { s -> s.category == category }
                        prizeAdapter = PrizeAdapter(catWisePrizeList, "Category", "Year")
                        binding.continentRecycler.adapter = prizeAdapter
                        prizeAdapter.notifyDataSetChanged()
                    }else if(category != "Category" && year != "Year") {
                        catWisePrizeList = prizeList.filter { s -> s.category == category && s.year==year }
                        prizeAdapter = PrizeAdapter(catWisePrizeList, "Category", "Year")
                        binding.continentRecycler.adapter = prizeAdapter
                        prizeAdapter.notifyDataSetChanged()
                    }


                }

            }


            yearAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, yearList)

            binding.yearSpin.adapter= yearAdapter
            yearAdapter.notifyDataSetChanged()

            binding.yearSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    year = yearList[position]
                    if(category == "Category" && year != "Year") {
                        catWisePrizeList = prizeList.filter { s -> s.year == year }
                        prizeAdapter = PrizeAdapter(catWisePrizeList, "Category", "Year")
                        binding.continentRecycler.adapter = prizeAdapter
                        prizeAdapter.notifyDataSetChanged()
                    }else if(category == "Category" && year == "Year") {
                        prizeAdapter = PrizeAdapter(finalPrizeList, "Category", "Year")
                        binding.continentRecycler.adapter = prizeAdapter
                        prizeAdapter.notifyDataSetChanged()
                    }else if(category != "Category" && year == "Year") {
                        catWisePrizeList = prizeList.filter { s -> s.category == category }
                        prizeAdapter = PrizeAdapter(catWisePrizeList, "Category", "Year")
                        binding.continentRecycler.adapter = prizeAdapter
                        prizeAdapter.notifyDataSetChanged()
                    }else if(category != "Category" && year != "Year") {
                        catWisePrizeList = prizeList.filter { s -> s.category == category && s.year==year }
                        prizeAdapter = PrizeAdapter(catWisePrizeList, "Category", "Year")
                        binding.continentRecycler.adapter = prizeAdapter
                        prizeAdapter.notifyDataSetChanged()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }


            }


            //bindUI(it)
        })




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
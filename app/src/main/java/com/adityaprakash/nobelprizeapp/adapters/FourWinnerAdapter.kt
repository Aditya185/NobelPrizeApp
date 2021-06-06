package com.adityaprakash.nobelprizeapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.adityaprakash.nobelprizeapp.R
import com.adityaprakash.nobelprizeapp.data.vo.Laureate
import kotlinx.android.synthetic.main.prize_layout.view.*

class FourWinnerAdapter(val prizeList: List<Laureate>,val category:String,val year:String) : RecyclerView.Adapter<FourWinnerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FourWinnerViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.laureate_layout,parent,false)
        return FourWinnerViewHolder(listItem)
    }



    override fun getItemCount(): Int {

        return prizeList.size
    }

    override fun onBindViewHolder(holder: FourWinnerViewHolder, position: Int) {
        val prize: Laureate = prizeList[position]
        holder.bindUI(prize)
    }
}

class FourWinnerViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    fun bindUI(prize: Laureate) {

        if (prize != null) {
            var name: String = ""
            if (prize.firstname != null) {
                name += prize.firstname
                if (prize.surname != null) {
                    name += prize.surname
                }
                view.category.text = name
            } else {
                view.category.text = "NA"
            }
            if (prize.motivation != null) {
                view.year.text = prize.motivation
            } else {
                view.year.text = "NA"
            }

//        view.setOnClickListener{
//            val bundle:Bundle = bundleOf("country_name" to country.country)
//            it.findNavController().navigate(R.id.action_nav_country_to_singleCountryFragment,bundle)
//        }


        }
    }
}
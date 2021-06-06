package com.adityaprakash.nobelprizeapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.adityaprakash.nobelprizeapp.R
import com.adityaprakash.nobelprizeapp.data.vo.Prize
import kotlinx.android.synthetic.main.prize_layout.view.*

class PrizeAdapter(val prizeList: List<Prize>,val category:String,val year:String) : RecyclerView.Adapter<PrizeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrizeViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.prize_layout,parent,false)
        return PrizeViewHolder(listItem)
    }



    override fun getItemCount(): Int {

        return prizeList.size
    }

    override fun onBindViewHolder(holder: PrizeViewHolder, position: Int) {
        val prize: Prize = prizeList[position]
        holder.bindUI(prize)
    }
}

class PrizeViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    fun bindUI(prize: Prize){

           if(prize != null) {
               if(prize.category != null) {
                   view.category.text = "Category: " + prize.category
               }else{
                   view.category.text = "Category: " + "NA"
               }
               if(prize.year != null) {
                   view.year.text = "Year-\n" + prize.year
               }else{
                   view.year.text = "Year-\n " + "NA"
               }
               var winners = prize.laureates
               if(winners != null) {
                   var winner_str = "Winners-\n"
                   for (i in winners) {
                       winner_str += i.firstname + " " + i.surname + "\n"
                   }
                   view.winners.text = winner_str
               }else{
                   view.winners.text = "Winners-\n NA"
               }
           }
//        view.setOnClickListener{
//            val bundle:Bundle = bundleOf("country_name" to country.country)
//            it.findNavController().navigate(R.id.action_nav_country_to_singleCountryFragment,bundle)
//        }


    }
}
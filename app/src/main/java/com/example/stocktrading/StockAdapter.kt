package com.example.stocktrading

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterClass(var stocksVal:MutableList<StockData>, var context:Context):RecyclerView.Adapter<AdapterClass.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater:LayoutInflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.items,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text1.text=stocksVal[position].name
        holder.text2.text="${stocksVal[position].price} $ per stock"

        Picasso.get().load(stocksVal[position].url).into(holder.image);
    }

    override fun getItemCount(): Int {
        return stocksVal.size
    }
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val image=itemView.findViewById<ImageView>(R.id.image)
        val text1=itemView.findViewById<TextView>(R.id.text)
        val text2=itemView.findViewById<TextView>(R.id.text2)
        val descriptionStock=itemView.findViewById<TextView>(R.id.descriptionStock)

        var id:Int=0

        init{
            itemView.setOnClickListener{
                id=adapterPosition
                val intent=Intent(context,StockOrders::class.java)
                intent.putExtra("ID",stocksVal[id].id)
                startActivity(context,intent, Bundle())
            }
        }

    }
}

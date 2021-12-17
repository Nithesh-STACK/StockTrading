package com.example.stocktrading

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyStockAdapter(var items:MutableList<StockData>, var context: Context, var application: Application):
    RecyclerView.Adapter<MyStockAdapter.ViewHolder>() {
    lateinit var sharedPreferenceManager: SharedPreferenceManager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStockAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.stock_orders,parent,false)
        return ViewHolder(view)

    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(items[position]!!.url).into(holder.img)
        holder.names.text=items[position].name
        holder.price.text="Total Cost $ ${items[position].price!!.toInt()*items[position]!!.price}"
        holder.countt.text="${items[position].price} Stocks"
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.imgg)
        var names=itemView.findViewById<TextView>(R.id.nametext)
        var price=itemView.findViewById<TextView>(R.id.pricetext)
        var countt=itemView.findViewById<TextView>(R.id.itemtext)
        var btn = itemView.findViewById<Button>(R.id.sellBtn)

        init {
            btn.setOnClickListener {
                var pos = adapterPosition
                var sessionManager = SharedPreferenceManager(context)
                var Api = application as StockApplication
                var token = sessionManager.fetchAuthToken()
                var intent = Intent(context,SellStocksActivity::class.java)
                intent.putExtra("owningId", items[pos].owningId)
                ContextCompat.startActivity(context, intent, Bundle())

            }
        }
    }
}
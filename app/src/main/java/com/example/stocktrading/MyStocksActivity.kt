package com.example.stocktrading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MyStocksActivity : AppCompatActivity() {
            lateinit var sharedPreference:SharedPreferenceManager
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_my_orders)
                var apiclient=application as StockApplication
                sharedPreference= SharedPreferenceManager(this)
                var stocks:MutableList<StockData> = mutableListOf<StockData>()
                var token=sharedPreference.fetchAuthToken()
                CoroutineScope(Dispatchers.IO).launch{
                    var result=apiclient.myStockService.fetchOrders("Bearer "+token)
                    if(result.isSuccessful) {
                        var i = 0
                        while(i<result.body()!!.stockOwnings!!.size){
                            var res=apiclient.stockService.GetStocks("Bearer "+token)
                            var j=0
                            while(j<res.body()!!.stocks!!.size){
                                if(result.body()!!.stockOwnings!![i].stockId==res.body()!!.stocks!![j].id)
                                    stocks.add(StockData(res.body()!!.stocks!![j].id,res.body()!!.stocks!![j].url,res.body()!!.stocks!![j].name,res.body()!!.stocks!![j].price))

                                j+=1

                            }
                            i+=1
                        }

                    }
                    withContext(Dispatchers.Main){
                        var recycle=findViewById<RecyclerView>(R.id.recyclerView)
                        recycle.adapter=MyStockAdapter(stocks,this@MyStocksActivity,application)
                        recycle.layoutManager= LinearLayoutManager(this@MyStocksActivity)
                    }
                }

            }
        }

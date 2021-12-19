package com.example.stocktrading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConfirmStocks : AppCompatActivity() {
    lateinit var sharedPreference:SharedPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_stocks)
        val img=findViewById<ImageView>(R.id.image)
        val qty=findViewById<TextView>(R.id.qty)
        val add=findViewById<Button>(R.id.add)
        val sub=findViewById<Button>(R.id.reduce)
        val pricce=findViewById<TextView>(R.id.itempricee)
        val create=findViewById<Button>(R.id.placeorder)
        var text=intent.getIntExtra("Id",0)
        var prices:Int?=0
        var count=0
        sharedPreference= SharedPreferenceManager(this)
        var token=sharedPreference.fetchAuthToken()
        var apiClient=application as StockApplication
        CoroutineScope(Dispatchers.IO).launch {
            val result=apiClient.stockService.GetStocks("Bearer "+token)
            var i=0
            var val1:StockData?=null
            if(result.isSuccessful){
                while(i<result.body()?.stocks!!.size){
                    if(result.body()?.stocks!![i].id==text){
                        val1=result.body()?.stocks!![i]
                        break
                    }
                    i+=1
                }
            }
            else{

            }
            withContext(Dispatchers.Main){
                Picasso.get().load(val1?.url).into(img)
                prices=val1?.price?.toInt()
            }
        }
        add.setOnClickListener{
            count+=1
            qty.text="$count"
            val pp=prices!!.toInt()
            pricce.text="$ ${pp*count}"
        }
        sub.setOnClickListener{
            if(count>0){
            count-=1
            }
            else
                count=0
            qty.text="$count"
            val pp=prices!!.toInt()
            pricce.text="$ ${pp*count}"
        }
        create.setOnClickListener{
            val loading = confirmStocksDialog(this)
            loading.startLoading()
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    loading.isDismiss()
                }
            }, 3000)
         if(count>0){
            CoroutineScope(Dispatchers.IO).launch {

                var result=apiClient.stockService.PlaceStockOrders("Bearer "+token, Stocks(text,count))
                withContext(Dispatchers.Main){
                    if(result!!.isSuccessful){
                        Toast.makeText(this@ConfirmStocks,"Stock Purchased successful",Toast.LENGTH_SHORT).show()
                    }
                }

                }
            }
         else{
             Toast.makeText(this@ConfirmStocks,"Please select number of stock items",Toast.LENGTH_SHORT).show()
         }
         }

        }
    }

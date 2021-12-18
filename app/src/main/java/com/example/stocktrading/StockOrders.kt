package com.example.stocktrading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StockOrders : AppCompatActivity() {
    lateinit var sharedPreference:SharedPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks_page)
        val img=findViewById<ImageView>(R.id.images)
        val name=findViewById<TextView>(R.id.name)
        val price=findViewById<TextView>(R.id.itemprice)
        val create=findViewById<Button>(R.id.Createorder)
        val description=findViewById<TextView>(R.id.descriptionStock)
        var text=intent.getIntExtra("ID",0)
        sharedPreference= SharedPreferenceManager(this)
        var token=sharedPreference.fetchAuthToken()
        var apiClient=application as StockApplication
        CoroutineScope(Dispatchers.IO).launch {
            val result=apiClient.stockService.GetStocks("Bearer "+ token)
            var i=0
            var res2:StockData?=null
            if(result.isSuccessful){
                while(i<result.body()?.stocks!!.size){
                    if(result.body()?.stocks!![i].id==text){
                        res2=result.body()?.stocks!![i]
                        break
                    }
                    i+=1
                }
            }
            else{

            }
            withContext(Dispatchers.Main){
                Picasso.get().load(res2?.url).into(img);
                name.text=res2?.name
                description.text="   ${res2?.description}"
                price.text="$ ${res2?.price}"
            }
        }
        create.setOnClickListener{
            var intent= Intent(this,ConfirmStocks::class.java)
            intent.putExtra("Id",text)
            startActivity(intent)
        }
        //Toast.makeText(this,"$text",Toast.LENGTH_SHORT).show()
    }
}
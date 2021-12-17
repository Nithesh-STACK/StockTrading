package com.example.stocktrading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SellStocksActivity : AppCompatActivity() {
    private lateinit var sharedPreference: SharedPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            var owningId = intent.getIntExtra("owningid", 0)
            sharedPreference = SharedPreferenceManager(this)
            val sampleApplication = application as StockApplication

            CoroutineScope(Dispatchers.IO).launch {
                var service = sampleApplication.delete.DeleteStocks(
                    "Bearer ${sharedPreference.fetchAuthToken()}",
                    owningId
                )

                withContext(Dispatchers.Main) {
                    if (service.isSuccessful) {
                        Toast.makeText(
                            this@SellStocksActivity,
                            "Stock Sold Successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@SellStocksActivity,
                            "Stocks Sold not Successfull",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }




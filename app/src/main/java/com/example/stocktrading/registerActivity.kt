package com.example.stocktrading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class registerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)




        val regButton = findViewById<Button>(R.id.RegisterBtn)
        regButton.setOnClickListener {

            val loading = LoadingDialogs(this)
            loading.startLoading()
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    loading.isDismiss()
                }
            }, 3000)
            val email = findViewById<TextInputLayout>(R.id.RegisterTextLayout).editText?.text
            val password = findViewById<TextInputLayout>(R.id.RegisterPassTextLayout).editText?.text
            val user=UserData(email.toString(),password.toString())

            CoroutineScope(Dispatchers.IO).launch {

                val regApplication = application as StockApplication
                val service = regApplication.registerService


                service.postRegData(user).enqueue(object :
                    Callback<registerDataClass?> {
                    override fun onFailure(call: Call<registerDataClass?>, t: Throwable) {
                        Toast.makeText(
                            this@registerActivity,
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<registerDataClass?>,
                        response: Response<registerDataClass?>
                    ) {
                        when {
                            response.code()==200 -> {


                                Toast.makeText(this@registerActivity, "Registration success!", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(this@registerActivity,LoginActivity::class.java)
                                startActivity(intent)


                            }
                            response.code()==500 -> {

                                Toast.makeText(
                                    this@registerActivity,
                                    "Registration failed!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                            }
                            else -> {
                                Toast.makeText(
                                    this@registerActivity,
                                    "User Already exists!!!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    }
                })
            }
        }
    }
}






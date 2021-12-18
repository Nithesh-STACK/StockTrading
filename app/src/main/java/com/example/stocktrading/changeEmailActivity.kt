package com.example.stocktrading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_change_email.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class changeEmailActivity : AppCompatActivity() {
    private lateinit var sharedPreference: SharedPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)
        val email=findViewById<TextInputLayout>(R.id.emailChangeLayout).editText?.text

        val confirmButton=findViewById<Button>(R.id.confirmChangeButton)
        confirmButton.setOnClickListener{
            val loading = changeEmailDialog(this)
            loading.startLoading()
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    loading.isDismiss()
                }
            }, 3000)
            val user=EmailUpdate(email.toString())
            sharedPreference = SharedPreferenceManager(this)
            CoroutineScope(Dispatchers.IO).launch {


                    val sampleApplication=application as StockApplication
                    val service=sampleApplication.emailupdate
                    service.ChangeEmail("Bearer ${sharedPreference.fetchAuthToken()}",user).enqueue(object : Callback<Void?> {
                        override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                            if(response.isSuccessful)
                            {
                                val intent = Intent(this@changeEmailActivity,LoginActivity::class.java)
                                startActivity(intent)
                                //Toast.makeText(applicationContext,response.body()?.email,Toast.LENGTH_LONG).show()

                            }
                            else
                            {

                            }
                        }

                        override fun onFailure(call: Call<Void?>, t: Throwable) {
                            //  TODO("Not yet implemented")
                        }
                    })
                }
            }
        }
    }
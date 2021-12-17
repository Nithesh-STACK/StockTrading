package com.example.stocktrading

import android.app.Application
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.lang.reflect.Type

class StockApplication:Application() {
    lateinit var registerService:RegisterApiInterface
    lateinit var loginService: loginApiInterface
    lateinit var stockService:StocksApiService
    lateinit var emailupdate:ChangeEmailApiService
    lateinit var delete:DeleteApiService
    lateinit var historyService:HistoryApiService
    lateinit var otherUserService: OtherUserApiService
    lateinit var myStockService:MyStocksApiService
    override fun onCreate() {
        super.onCreate()
        delete = initHttpDeleteApiService()
        loginService = loginApi()
        registerService = initHttpregisterApiService()
        stockService = initHttpApiService()
        emailupdate = initUpdateHttpApiService()
        historyService = HistoryHttpApiService()
        otherUserService = OtherUserHttpApiService()
        myStockService = MyStockHttpApiService()

    }
    private fun MyStockHttpApiService():MyStocksApiService{

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(MyStocksApiService::class.java)
    }
    fun loginApi(): loginApiInterface
    {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/priceapp-secure-backend/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(loginApiInterface::class.java)
    }

    private fun OtherUserHttpApiService():OtherUserApiService{

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create( OtherUserApiService::class.java)
    }
    fun initHttpregisterApiService(): RegisterApiInterface {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/priceapp-secure-backend/")
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RegisterApiInterface::class.java)
    }
    private fun initHttpApiService():StocksApiService{

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        return retrofit.create(StocksApiService::class.java)
    }
    private fun HistoryHttpApiService():HistoryApiService{

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        return retrofit.create(HistoryApiService::class.java)
    }
    private fun initUpdateHttpApiService():ChangeEmailApiService{

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ChangeEmailApiService::class.java)
    }
    fun initHttpDeleteApiService():DeleteApiService  {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/")
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(DeleteApiService::class.java)
    }



    class NullOnEmptyConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type?,
            annotations: Array<Annotation>?,
            retrofit: Retrofit?
        ): Converter<ResponseBody, *>? {
            val delegate = retrofit!!.nextResponseBodyConverter<Any>(this, type!!, annotations!!)
            return Converter<ResponseBody, Any> {
                if (it.contentLength() == 0L) return@Converter
                delegate.convert(it)
            }
        }
    }
}


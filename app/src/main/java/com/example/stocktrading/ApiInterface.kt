package com.example.stocktrading

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface loginApiInterface {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun postData(@Body users:UserData): Call<loginDataClass>


}
interface RegisterApiInterface {
    @Headers("Content-Type: application/json")
    @POST("register")
    fun postRegData(@Body reg:UserData): Call<registerDataClass>
}
interface StocksApiService {
    @GET("/priceapp-secure-backend/stocks")
    suspend fun GetStocks(@Header("Authorization") token: String): Response<StocksList>

    @POST("/priceapp-secure-backend/users/me/ownings")
    suspend fun PlaceStockOrders(
        @Header("Authorization") token: String,
        @Body request: Stocks
    ): Response<Unit>



}
interface HistoryApiService {
    @GET("/priceapp-secure-backend/users/me/loginHistory")
    suspend fun logDetails(@Header("Authorization") token: String): Response<LogList>
}

interface OtherUserApiService {
    @GET("/priceapp-secure-backend/users")
    suspend fun otherUserDetails(@Header("Authorization") token: String): Response<otherUserList>
}

interface ChangeEmailApiService {
    @Headers("Content-Type: application/json")

    @POST("/priceapp-secure-backend/users/me/email")
    fun ChangeEmail(@Header("Authorization") token: String, @Body user: EmailUpdate): Call<Void>

}

interface DeleteApiService {
    @Headers("Content-Type: application/json")
    @DELETE("priceapp-secure-backend/users/me")
    fun deleteUser(@Header("Authorization") token: String): Call<Void>
    @DELETE("/priceapp-secure-backend/users/me/ownings/{owningId}")
    suspend fun DeleteStocks(@Header("Authorization") token: String,@Path("owningId")
    owningId:Int):Response<Unit>

}
interface  MyStocksApiService{

    @GET("priceapp-secure-backend/users/me/ownings")
    suspend fun fetchOrders(@Header("Authorization") token: String):Response<MyStockList>
}
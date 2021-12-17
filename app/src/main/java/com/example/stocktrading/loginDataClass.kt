package com.example.stocktrading

data class loginDataClass(
    val id:Int,
    val email:String,
    val password:String,
    val token:String,
    val memberSince:Long
)

data class UserData(val email: String?="",val password: String?="") {
}
data class  EmailUpdate(val email: String){

}

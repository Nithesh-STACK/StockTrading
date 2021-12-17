package com.example.stocktrading

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*

class loginHistoryAdapterClass(var logsVal:MutableList<LoginHistoryDataClass>, var context: Context):
    RecyclerView.Adapter<loginHistoryAdapterClass.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): loginHistoryAdapterClass.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.logins,parent,false)
        return ViewHolder(view)

    } override fun getItemCount(): Int {
        return logsVal.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val logindate=itemView.findViewById<TextView>(R.id.LoginDates)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sdf = SimpleDateFormat("dd-MMM-YYYY")
        val netDate = Date(logsVal[position].loginTimestamp)
        val displayThisDate = sdf.format(netDate)

  holder.logindate.text="${displayThisDate}"

    }
}
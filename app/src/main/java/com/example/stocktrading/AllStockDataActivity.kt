package com.example.stocktrading

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktrading.databinding.ActivityDrawnBinding
import kotlinx.android.synthetic.main.activity_drawermenu.*
import kotlinx.android.synthetic.main.activity_drawn.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrawnActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDrawnBinding
    private lateinit var sharedPreference: SharedPreferenceManager
    lateinit var toggle:ActionBarDrawerToggle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_drawn)
        var profileIntent=Intent(this@DrawnActivity,profileActivity::class.java)
        var myStockjIntent=Intent(this@DrawnActivity,MyStocksActivity::class.java)
        var otherUserIntent=Intent(this@DrawnActivity,otherUserActivity::class.java)
        val navview=findViewById<NavigationView>(R.id.navView)
        val drawerlay=findViewById<DrawerLayout>(R.id.drawerLayout)
        val drawerEmailId = navview.getHeaderView(0).findViewById<TextView>(R.id.emailDrawerText)
        sharedPreference = SharedPreferenceManager(this)
        drawerEmailId.text = "${sharedPreference.fetchEmail()}"


        toggle= ActionBarDrawerToggle(this,drawerlay,toolbar,R.string.open,R.string.close)

        drawerlay.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.splashscreenlogo)
        navview.setNavigationItemSelectedListener{
            when(it.itemId)
            {
                R.id.profileId-> {startActivity(profileIntent)}
                R.id.myOrdersList -> {startActivity(myStockjIntent)}
                R.id.otherUserList -> {startActivity(otherUserIntent)}
            }
            true
        }


        val string = intent.getStringExtra("string")
        val apiclient = application as StockApplication
        sharedPreference = SharedPreferenceManager(this)
        var intent = Intent(this, LoginActivity::class.java)

        var token = sharedPreference.fetchAuthToken()
        val items: MutableList<StockData> = mutableListOf<StockData>()
        if (sharedPreference.fetchAuthToken() != null) {
            CoroutineScope(Dispatchers.IO).launch {
                val result = apiclient.stockService.GetStocks("Bearer " + token)
                var i = 0
                if (result.isSuccessful) {
                    while (i < result.body()?.stocks!!.size) {
                        items.add(result.body()?.stocks!![i])
                        i += 1
                    }
                } else {
                    startActivity(intent)
                }
                withContext(Dispatchers.Main) {
                    val recycle = findViewById<RecyclerView>(R.id.recycle)
                    recycle.adapter = AdapterClass(items,this@DrawnActivity)
                    recycle.layoutManager = LinearLayoutManager(this@DrawnActivity)
                }

            }
        } else {
            startActivity(intent)
        }
        supportActionBar?.hide()
        setSupportActionBar(toolbar)


    }


}
// binding = ActivityDrawnBinding.inflate(layoutInflater)
// setContentView(binding.root)
//
// setSupportActionBar(binding.appBarDrawn.toolbar)
//
// binding.appBarDrawn.fab.setOnClickListener { view ->
// Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
// .setAction("Action", null).show()
// }
// val drawerLayout: DrawerLayout = binding.drawerLayout
// val navView: NavigationView = binding.navView
// val navController = findNavController(R.id.nav_host_fragment_content_drawn)
// // Passing each menu ID as a set of Ids because each
// // menu should be considered as top level destinations.
// appBarConfiguration = AppBarConfiguration(
// setOf(
// R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
// ), drawerLayout
// )
// setupActionBarWithNavController(navController, appBarConfiguration)
// navView.setupWithNavController(navController)
// }
//
// override fun onCreateOptionsMenu(menu: Menu): Boolean {
// // Inflate the menu; this adds items to the action bar if it is present.
// menuInflater.inflate(R.menu.drawn, menu)
// return true
// }
//
// override fun onSupportNavigateUp(): Boolean {
// val navController = findNavController(R.id.nav_host_fragment_content_drawn)
// return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
// }
// }
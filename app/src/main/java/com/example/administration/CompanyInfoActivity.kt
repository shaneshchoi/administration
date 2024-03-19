package com.example.administration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CompanyInfoActivity : AppCompatActivity() {

    //create an array holding three strings
    val tabsArray = arrayOf("Globalized", "Products", "Career")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_info)

        val viewPager = findViewById<ViewPager2>(R.id.viewpager)
        val tablayout = findViewById<TabLayout>(R.id.tablayout)

        val my_adapter = ViewPageAdapter(
            supportFragmentManager,
            lifecycle
        )
        //Allows to view three strings in the array to be attached together on the top part
        viewPager.adapter = my_adapter
        TabLayoutMediator(tablayout, viewPager){
                tab,position -> tab.text = tabsArray[position]
        }.attach()
    }
}
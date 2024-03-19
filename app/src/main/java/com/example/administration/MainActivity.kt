package com.example.administration


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var btn: ImageView
    private lateinit var layout: DrawerLayout
    private lateinit var navi: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btn_navi)
        layout = findViewById(R.id.layout_drawer)
        navi = findViewById(R.id.naviView)

        btn.setOnClickListener {
            layout.openDrawer(GravityCompat.START)
            //If clicked on the button, open the drawer on the left side

        }
        navi.setNavigationItemSelectedListener(this)
        // Get the admin status from LoginActivity
        // If signed in as admin, should be able to see emloyeesMenu
        val intent = intent
        val isAdmin: Boolean = intent.getBooleanExtra("admin", false)
        val isSignedIn: Boolean = intent.getBooleanExtra("signIn", false)

        val menu = navi.menu
        val employeesMenuItem = menu.findItem(R.id.employees)
        val loginMenuItem = menu.findItem(R.id.login)
        val logoutMenuItem = menu.findItem(R.id.logout)

        employeesMenuItem.isVisible = isAdmin
        loginMenuItem.isVisible = !isSignedIn
        logoutMenuItem.isVisible = isSignedIn
    }
    //Run if clicked on nav menus
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.login ->{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.logout ->{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.companyInfo -> {
                val intent = Intent(this, CompanyInfoActivity::class.java)
                startActivity(intent)
            }
            R.id.employees -> {
                    val intent = Intent(this, EmployeesListActivity::class.java)
                    startActivity(intent)
            }
        }
        //close nav view
        layout.closeDrawers()
        return false
    }
}
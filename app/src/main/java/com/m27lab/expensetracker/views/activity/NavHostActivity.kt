package com.m27lab.expensetracker.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.m27lab.expensetracker.R
import com.m27lab.expensetracker.utils.Utils
import kotlinx.android.synthetic.main.activity_nav_host.*

class NavHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)

        Utils.setWhiteStatusBar(this)

        //step 1:
        //java:var navController= Navigation.findNavController(this,R.id.host_fragment)
        var navController = host_fragment.findNavController()
        //setp 2
        // Setting Navigation Controller with the BottomNavigationView
        //NavigationUI.setupWithNavController(bottom_nav, navController)---java
        bottom_nav.setupWithNavController(navController)
    }
}
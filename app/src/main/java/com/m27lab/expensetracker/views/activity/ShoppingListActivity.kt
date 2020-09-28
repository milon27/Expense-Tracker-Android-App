package com.m27lab.expensetracker.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.m27lab.expensetracker.utils.Utils

class ShoppingListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        //set status bar white
        Utils.setWhiteStatusBar(this)
    }
}
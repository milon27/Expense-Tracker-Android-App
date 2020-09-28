package com.m27lab.expensetracker.utils.cache

import android.app.Activity
import android.content.Context

class SharedPref{
    companion object{
        //define
        val DEF_BUGET="def_budget"
        fun setBudget(context: Context,value:Double){
            (context as Activity).getPreferences(Context.MODE_PRIVATE)
                .edit().putString(DEF_BUGET,value.toString()).apply()
        }
        fun getBudget(context: Context):String{
            return (context as Activity).getPreferences(Context.MODE_PRIVATE).getString(DEF_BUGET,"0.0").toString()
        }
    }





}
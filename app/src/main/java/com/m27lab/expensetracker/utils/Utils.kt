package com.m27lab.expensetracker.utils

import android.app.Activity
import android.content.Context
import android.view.View
import kotlinx.coroutines.*

object Utils {
    //hide any view after N seconds
     fun hideView(time: Long,vararg views: View){
        CoroutineScope(Dispatchers.Default).launch{
            delay(time)
            withContext(Dispatchers.Main){
                //view.visibility= View.GONE
                for (v in views){
                    v.visibility= View.GONE
                }
            }
        }
    }

    // set status background white
    fun setWhiteStatusBar(context: Context) {
        /*
        (context as Activity).window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark

        context.window.statusBarColor = ContextCompat.getColor(
            context,
            android.R.color.white
        ) // set status background white
        */
        (context as Activity).window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    //oikik nityon
    fun Unitary(budget:Double,expense:Double):Double{
        return if(budget==0.0) {
            0.0
        }else {
            (expense*100)/budget;
        }
    }

    //shared preference
//    fun setBudget(context:Context,value:Double)
//    {
//        val sharedPref =  (context as Activity).getPreferences(Context.MODE_PRIVATE)
//        sharedPref.edit().putString("budget",value.toString()).apply()
//    }


    //text watcher funtion


//    val textWatcher=object:TextWatcher{
//        override fun afterTextChanged(s: Editable?) {
//        }
//
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        }
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            val text:String=s.toString().trim()
//            if(!text.isEmpty() && text.length>2){
//                viewModel.searchNow(text).observe(this@ShoppingListActivity, Observer
//                {
//                    adapter.items=it
//                    list.addAll(it)
//                    adapter.notifyDataSetChanged()
//                })
//            }else{
//                viewModel.getAllItems().observe(this@ShoppingListActivity, Observer {
//                    adapter.items=it
//                    //Log.d("TAG", "onCreate: ${list.size}   "+it.get(0).title)
//                    adapter.notifyDataSetChanged()
//                })
//            }
//        }
//    }
}
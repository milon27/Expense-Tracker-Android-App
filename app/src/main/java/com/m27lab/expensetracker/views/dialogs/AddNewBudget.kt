package com.m27lab.expensetracker.views.dialogs

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.m27lab.expensetracker.R
import kotlinx.android.synthetic.main.layout_add_budget.*

class AddNewBudget(context: Context,private val onSetBudget: OnSetBudgetListener):AlertDialog(context,R.style.AlertDialogStyle) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_add_budget)

        //clear flags
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)

        //get & set from submit button
        submit_btn.setOnClickListener{
            val value=total_budget.text.toString().trim()
            if(value.isEmpty() || value.toDouble()==0.0){
                Toast.makeText(context, "Enter A Budget", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            onSetBudget.onSetBudget(value.toDouble())
            dismiss()
        }
    }
}

interface OnSetBudgetListener{
    fun onSetBudget(value:Double)
}
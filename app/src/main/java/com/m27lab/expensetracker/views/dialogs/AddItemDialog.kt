package com.m27lab.expensetracker.views.dialogs

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.m27lab.expensetracker.R
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import kotlinx.android.synthetic.main.layout_add_new.*
import java.util.*


class AddItemDialog(
    context: Context,
    private val listener: OnAddItemListener
) : AlertDialog(context,R.style.AlertDialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_add_new)
        //clear flags
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)

        submit_btn.setOnClickListener(View.OnClickListener {

            val title=title.text.toString().trim()
            val price=price.text.toString().trim()
            val desc=""
            val date=Date()

            if(title.isEmpty() || price.isEmpty()){
                Toast.makeText(context, "fill up the form", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            val item =ExpenseEntity(title,price.toDouble(),date)
            item.description=desc
            listener.onAddItem(item)
            dismiss()
        })

    }
}

interface OnAddItemListener {
    fun onAddItem(item: ExpenseEntity)
}

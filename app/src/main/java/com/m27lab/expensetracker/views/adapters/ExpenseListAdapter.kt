package com.m27lab.expensetracker.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.m27lab.expensetracker.R
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import com.m27lab.expensetracker.utils.Utils
import com.m27lab.expensetracker.viewmodels.ExpenseViewModel
import kotlinx.android.synthetic.main.layout_shopping.view.*


class ExpenseListAdapter(
    var context: Context,
    var items:List<ExpenseEntity>,
    var viewModel:ExpenseViewModel
)
    :RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_shopping,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item=items.get(position)
        holder.itemView.title.setText(item.title)
        holder.itemView.price.setText(item.price.toString())

        holder.itemView.layoutID.setOnLongClickListener(object :View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                if(
                    holder.itemView.deleteItem.visibility==View.VISIBLE
                ){
                    holder.itemView.deleteItem.visibility=View.GONE
                }else{
                    holder.itemView.deleteItem.visibility=View.VISIBLE
                    Utils.hideView(1600,holder.itemView.deleteItem)
                }
                return false
            }
        })

        holder.itemView.deleteItem.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                viewModel.delete(context,item)
            }
        })

    }

     class ExpenseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}



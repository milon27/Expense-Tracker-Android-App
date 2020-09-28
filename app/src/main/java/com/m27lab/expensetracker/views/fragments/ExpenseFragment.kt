package com.m27lab.expensetracker.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.m27lab.expensetracker.R
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import com.m27lab.expensetracker.utils.Utils
import com.m27lab.expensetracker.utils.cache.SharedPref
import com.m27lab.expensetracker.viewmodels.ExpenseViewModel
import com.m27lab.expensetracker.views.adapters.ExpenseListAdapter
import com.m27lab.expensetracker.views.dialogs.AddItemDialog
import com.m27lab.expensetracker.views.dialogs.AddNewBudget
import com.m27lab.expensetracker.views.dialogs.OnAddItemListener
import com.m27lab.expensetracker.views.dialogs.OnSetBudgetListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_expense.*

@AndroidEntryPoint
class ExpenseFragment : Fragment(R.layout.fragment_expense) {
    private val TAG = "NoteFragment"
    private val viewModel: ExpenseViewModel by viewModels()
    private lateinit var adapter:ExpenseListAdapter
    private lateinit var list: ArrayList<ExpenseEntity>
    private lateinit var contxt:Context

    private  var total_expense:Double=0.0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contxt=context
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set status bar white
        Utils.setWhiteStatusBar(contxt)
        //init
        list= ArrayList()
        //viewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java);
        adapter= ExpenseListAdapter(contxt,list,viewModel)

        budget.text="Budget : ${SharedPref.getBudget(contxt)} tk"

        //setup recyclerview
        rv_expenseList.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        rv_expenseList.adapter=adapter
        rv_expenseList.setHasFixedSize(true)


        //get expense list which alredy purched
        viewModel.getAllExpenseItems().observe(viewLifecycleOwner, Observer{
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })
        //get total price
        viewModel.getTotalExpense().observe(viewLifecycleOwner, Observer{
            if(it==null){
                total_expense=0.0
            }else{
                total_expense=it
            }
            expense.text="Expense : $total_expense tk"
            balance.text="Balance : ${SharedPref.getBudget(contxt).toDouble()-total_expense} tk"
            val percentage=Utils.Unitary(SharedPref.getBudget(contxt).toDouble(),total_expense)
            percent.text=percentage.toString()+"/100%"
            pbar_hoz.progress=percentage.toInt()
        })

        //add expense item
        fabBtn.setOnClickListener{
            AddItemDialog(contxt, object : OnAddItemListener {
                override fun onAddItem(item: ExpenseEntity) {
                    //Log.d(TAG, "onAddItem: $item")
                    item.purchased=true
                    viewModel.set(item)
                    Toast.makeText(contxt, "Expense Item Added ", Toast.LENGTH_SHORT).show()
                }
            }).show()
        }
        //set budget
        budget.setOnClickListener{
            AddNewBudget(contxt, object : OnSetBudgetListener {
                override fun onSetBudget(value: Double) {
                    budget.text="Budget : $value tk"
                    SharedPref.setBudget(contxt,value)
                    //ui update
                    balance.text="Balance : ${value-total_expense} tk"
                    val percentage=Utils.Unitary(value,total_expense)
                    percent.text=percentage.toString()+"/100%"
                    pbar_hoz.progress=percentage.toInt()
                }
            }).show()
        }
    }
}

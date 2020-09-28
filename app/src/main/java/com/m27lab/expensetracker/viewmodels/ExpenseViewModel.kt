package com.m27lab.expensetracker.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.m27lab.expensetracker.data.db.ShoppingDB
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import com.m27lab.expensetracker.data.repository.ExpenseRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class ExpenseViewModel : ViewModel(){
    fun set(context:Context,item:ExpenseEntity): Job {
        val db = ShoppingDB.invoke(context = context);
        val repo = ExpenseRepo(db)
        return CoroutineScope(Dispatchers.IO).launch {
            repo.set(item)
        }
    }


    fun delete(context:Context,item:ExpenseEntity):Job{
        val db = ShoppingDB.invoke(context = context);
        val repo = ExpenseRepo(db);
        return CoroutineScope(Dispatchers.IO).launch {
            repo.delete(item)
        }
    }

    fun getAllItems(context:Context):LiveData<List<ExpenseEntity>>{
        val db = ShoppingDB.invoke(context = context);
        val repo = ExpenseRepo(db);
        return repo.getAllItem();
    }

    fun getAllExpenseItems(context:Context):LiveData<List<ExpenseEntity>>{
        val db = ShoppingDB.invoke(context = context);
        val repo = ExpenseRepo(db);
        return repo.getAllExpenseItem();
    }

    fun searchNow(context:Context,string: String):LiveData<List<ExpenseEntity>>{
        val db = ShoppingDB.invoke(context = context);
        val repo = ExpenseRepo(db);
        return repo.searchNow(string);
    }

    fun getTotalExpense(context:Context):LiveData<Double>{
        val db = ShoppingDB.invoke(context = context);
        val repo = ExpenseRepo(db);
        return repo.getTotalExpense();
    }

    fun getToday(context: Context){
        val db = ShoppingDB.invoke(context = context);
        val repo = ExpenseRepo(db);
        CoroutineScope(Dispatchers.IO).launch {
            repo.getToday()
        }
    }

    fun getInRange(context: Context, start: Date, end:Date){
        val db = ShoppingDB.invoke(context = context);
        val repo = ExpenseRepo(db);
        CoroutineScope(Dispatchers.IO).launch {
            repo.getInRange(start,end)
        }
    }
}
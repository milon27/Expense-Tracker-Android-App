package com.m27lab.expensetracker.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import com.m27lab.expensetracker.data.repository.ExpenseRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class ExpenseViewModel @ViewModelInject constructor(private val repo:ExpenseRepo) : ViewModel(){
    fun set(item:ExpenseEntity): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            repo.set(item)
        }
    }


    fun delete(item:ExpenseEntity):Job{
        return CoroutineScope(Dispatchers.IO).launch {
            repo.delete(item)
        }
    }

    fun getAllItems():LiveData<List<ExpenseEntity>>{
        return repo.getAllItem();
    }

    fun getAllExpenseItems():LiveData<List<ExpenseEntity>>{
        return repo.getAllExpenseItem();
    }

    fun searchNow(string: String):LiveData<List<ExpenseEntity>>{
        return repo.searchNow(string);
    }

    fun getTotalExpense():LiveData<Double>{
        return repo.getTotalExpense();
    }

    fun getToday(){
        CoroutineScope(Dispatchers.IO).launch {
            repo.getToday()
        }
    }

    fun getInRange(start: Date, end:Date){
        CoroutineScope(Dispatchers.IO).launch {
            repo.getInRange(start,end)
        }
    }


}
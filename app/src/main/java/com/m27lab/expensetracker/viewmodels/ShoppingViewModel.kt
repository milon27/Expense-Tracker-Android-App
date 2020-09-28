package com.m27lab.expensetracker.viewmodels

import androidx.lifecycle.ViewModel
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import com.m27lab.expensetracker.data.repository.ExpenseRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@repo require a DB instantce
class ShoppingViewModel(private val repo: ExpenseRepo) : ViewModel() {

    fun set(itemTable: ExpenseEntity)= CoroutineScope(Dispatchers.Main)
        .launch {repo.set(itemTable);}

    fun delete(itemTable: ExpenseEntity)= CoroutineScope(Dispatchers.Main)
        .launch { repo.delete(itemTable);}

    fun getAllItems()=repo.getAllItem();

    fun searchNow(string: String)=repo.searchNow(string);

}
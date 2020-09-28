package com.m27lab.expensetracker.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.m27lab.expensetracker.data.repository.ExpenseRepo
import com.m27lab.expensetracker.viewmodels.ExpenseViewModel

@Suppress("UNCHECKED_CAST")
class ExpenseViewModelFactory(val repo: ExpenseRepo):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExpenseViewModel() as T
    }
}
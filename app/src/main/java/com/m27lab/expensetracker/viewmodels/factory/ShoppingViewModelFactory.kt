package com.m27lab.expensetracker.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.m27lab.expensetracker.data.repository.ExpenseRepo
import com.m27lab.expensetracker.viewmodels.ExpenseViewModel
import com.m27lab.expensetracker.viewmodels.ShoppingViewModel


//not used in this project
@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(val repo: ExpenseRepo):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repo) as T
    }
}
package com.m27lab.expensetracker.data.repository

import com.m27lab.expensetracker.data.db.ShoppingDB
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import java.util.*

class ExpenseRepo(private val db: ShoppingDB) {

    suspend fun set(expenseEntity: ExpenseEntity)=db.getExpenseDAO().set(expenseEntity)

    suspend fun delete(item: ExpenseEntity)=db.getExpenseDAO().delete(item)

    fun getAllItem()=db.getExpenseDAO().getAllItem()

    fun getAllExpenseItem()=db.getExpenseDAO().getAllExpenseItem()

    fun searchNow(string: String)=db.getExpenseDAO().search(string)

    fun getTotalExpense()=db.getExpenseDAO().getTotalExpense()


    fun getToday()=db.getExpenseDAO().getToday(Date())

    fun getInRange(start:Date,end:Date)=db.getExpenseDAO().getInRange(start,end)

}
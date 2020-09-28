package com.m27lab.expensetracker.data.repository

import com.m27lab.expensetracker.data.db.dao.ExpenseDAO
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import java.util.*
import javax.inject.Inject

class ExpenseRepo @Inject constructor(private val dao:ExpenseDAO) {

    suspend fun set(expenseEntity: ExpenseEntity)=dao.set(expenseEntity)

    suspend fun delete(item: ExpenseEntity)=dao.delete(item)

    fun getAllItem()=dao.getAllItem()

    fun getAllExpenseItem()=dao.getAllExpenseItem()

    fun searchNow(string: String)=dao.search(string)

    fun getTotalExpense()=dao.getTotalExpense()


    fun getToday()=dao.getToday(Date())

    fun getInRange(start:Date,end:Date)=dao.getInRange(start,end)

}
package com.m27lab.expensetracker.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import java.util.*

@Dao
interface ExpenseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun set(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun delete(expenseEntity: ExpenseEntity)

    @Query("SELECT * FROM expense_table WHERE title LIKE '%'||:search||'%' ")
    fun search(search:String) : LiveData<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table where purchased=0 Order by id DESC")
    fun getAllItem():LiveData<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table where purchased=1 Order by id DESC")
    fun getAllExpenseItem():LiveData<List<ExpenseEntity>>

    @Query("SELECT sum(price) as total_expense FROM expense_table where purchased=1 ")
    fun getTotalExpense():LiveData<Double>








    @Query(" select * from expense_table where transaction_date = :date ")
    fun getToday(date: Date):LiveData<List<ExpenseEntity>>

    @Query("select * from expense_table where transaction_date between :start and :end ")
    fun getInRange(start:Date,end:Date):LiveData<List<ExpenseEntity>>
}
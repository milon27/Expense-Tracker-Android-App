package com.m27lab.expensetracker.di.module

import android.content.Context
import androidx.room.Room
import com.m27lab.expensetracker.data.db.ShoppingDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DbModule {
    //provide singleton db instance using hilt
    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): ShoppingDB {
        return Room.databaseBuilder(
            context,
            ShoppingDB::class.java,
            "shopping_db"
        ).build()
    }

    //provide the expense singleton dao
    @Singleton
    @Provides
    fun provideExpenseDAO(db:ShoppingDB)=db.getExpenseDAO()


}
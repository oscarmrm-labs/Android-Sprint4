package com.qualentum.sprint4.di

import android.content.Context
import androidx.room.Room
import com.qualentum.sprint4.data.room.AppDatabase
import com.qualentum.sprint4.data.room.dao.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactsModule {
    private const val DATABASE = "database"

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE).build()

    @Provides
    @Singleton
    fun provideContactDAO(appDatabase: AppDatabase): ContactDao =
        appDatabase.contactDao()
}
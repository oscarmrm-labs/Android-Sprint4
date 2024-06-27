package com.qualentum.sprint4.di

import android.content.Context
import androidx.room.Room
import com.qualentum.sprint4.data.AppDatabase
import com.qualentum.sprint4.data.dao.ContactDao
import com.qualentum.sprint4.data.repository.ContactsRepository
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import com.qualentum.sprint4.presentation.contacts.ContactsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactsModule {


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()


    @Provides
    @Singleton
    fun provideContactsViewModel(
        contactsUseCases: ContactsUseCases
    ): ContactsViewModel {
        return ContactsViewModel (
            contactsUseCases
        )
    }

    @Provides
    @Singleton
    fun provideContactsUseCases(repository: ContactsRepository): ContactsUseCases {
        return ContactsUseCases(repository)
    }

    @Provides
    @Singleton
    fun provideContactRepository(contactDao: ContactDao): ContactsRepository {
        return ContactsRepository(contactDao)
    }

    @Provides
    @Singleton
    fun provideContactDAO(appDatabase: AppDatabase): ContactDao {
        return appDatabase.contactDao()
    }
}
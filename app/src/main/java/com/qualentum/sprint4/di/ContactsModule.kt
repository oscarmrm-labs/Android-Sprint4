package com.qualentum.sprint4.di

import android.content.Context
import androidx.room.Room
import com.qualentum.sprint4.data.AppDatabase
import com.qualentum.sprint4.data.dao.ContactDao
import com.qualentum.sprint4.data.repository.ContactsRepository
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import com.qualentum.sprint4.presentation.common.location.ManageLocation
import com.qualentum.sprint4.presentation.contacts.ContactsViewModel
import com.qualentum.sprint4.presentation.favourites.FavouritesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactsModule {
    const val DATABASE = "database"

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE).build()


    @Provides
    @Singleton
    fun provideContactsViewModel(contactsUseCases: ContactsUseCases): ContactsViewModel =
        ContactsViewModel (contactsUseCases)


    @Provides
    @Singleton
    fun provideFavouriteContactsViewModel(contactsUseCases: ContactsUseCases): FavouritesViewModel =
        FavouritesViewModel (contactsUseCases)


    @Provides
    @Singleton
    fun provideContactsUseCases(repository: ContactsRepository): ContactsUseCases =
        ContactsUseCases(repository)

    @Provides
    @Singleton
    fun provideContactRepository(contactDao: ContactDao): ContactsRepository =
        ContactsRepository(contactDao)

    @Provides
    @Singleton
    fun provideContactDAO(appDatabase: AppDatabase): ContactDao =
        appDatabase.contactDao()

    @Provides
    @Singleton
    fun provideManageLocation(): ManageLocation = ManageLocation()
}
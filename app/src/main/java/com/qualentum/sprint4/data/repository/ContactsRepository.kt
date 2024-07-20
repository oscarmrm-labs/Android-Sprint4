package com.qualentum.sprint4.data.repository

import com.qualentum.sprint4.data.room.dao.ContactDao
import com.qualentum.sprint4.data.room.entity.ContactEntity
import javax.inject.Inject

class ContactsRepository @Inject constructor(
    private val contactDao: ContactDao
) {
    suspend fun getAllContacts(): List<ContactEntity> = contactDao.getAllContacts()

    suspend fun insertContact(contact: ContactEntity) = contactDao.insertContact(contact)

    suspend fun getContactById(id: Int): ContactEntity = contactDao.getContactById(id)

    suspend fun getFilteredContact(filter: String?): List<ContactEntity> = contactDao.getFilteredContact(filter)

    suspend fun deleteContact(id: Int?) = contactDao.deleteContactById(id)

    suspend fun updateFavouriteContact(id: Int?, isFavourite: Boolean?) = contactDao.updateFavouriteContact(id, isFavourite)

    suspend fun getAllFavouritesContacts(): List<ContactEntity> = contactDao.getAllFavouritesContacts()
}
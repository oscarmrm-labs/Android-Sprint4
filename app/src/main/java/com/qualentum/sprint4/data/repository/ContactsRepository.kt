package com.qualentum.sprint4.data.repository

import com.qualentum.sprint4.data.dao.ContactDao
import com.qualentum.sprint4.data.entity.ContactEntity
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.domain.model.DetailContactModel
import javax.inject.Inject

class ContactsRepository @Inject constructor(
    private val contactDao: ContactDao
) {
    suspend fun getContacts(): List<ContactEntity> = contactDao.getAllContacts()

    suspend fun insertContact(contact: DetailContactModel) {
        val entity = ContactEntity(
            firstName = contact.name,
            lastName = contact.lastName,
            dateOfBirth = contact.dateOfBirth,
            favouriteColorHex = contact.favouriteColorHex,
            favouriteSport = contact.favouriteSport,
        )
        contactDao.insertContact(entity)
    }

    suspend fun getContactById(id: Int): ContactEntity = contactDao.getContactById(id)
}
package com.qualentum.sprint4.data.repository

import com.qualentum.sprint4.data.dao.ContactDao
import com.qualentum.sprint4.data.entity.ContactEntity
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.domain.model.DetailContactModel

class ContactsRepository(private val contactDao: ContactDao) {
    suspend fun getContacts(): List<ContactModel> {
        val entity = contactDao.getAllContacts()
        return entity.map {
            ContactModel(
                name = it.firstName,
                lastName = it.lastName
            )
        }
    }

    suspend fun insertContact(contact: DetailContactModel) {
        val entity = ContactEntity(
            firstName = contact.name,
            lastName = contact.lastName,
            dateOfBirth = contact.dateOfBirth,
            //favouriteColorHex = contact.favouriteColorHex,
            //favouriteSport = contact.favouriteSport,
        )
        contactDao.insertContact(entity)
    }
}
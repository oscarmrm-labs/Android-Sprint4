package com.qualentum.sprint4.data.repository

import com.qualentum.sprint4.data.dao.ContactDao
import com.qualentum.sprint4.data.entity.ContactEntity
import com.qualentum.sprint4.data.model.Contact

class ContactsRepository(private val contactDao: ContactDao) {
    suspend fun getContacts(): List<Contact> {
        val entity = contactDao.getAllContacts()
        return entity.map {
            Contact(
                name = it.name,
                age = it.age
            )
        }
    }

    suspend fun insertContact(contact: Contact) {
        val entity = ContactEntity(name = contact.name, age = contact.age) // TODO: mapper
        contactDao.insertContact(entity)
    }
}
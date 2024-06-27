package com.qualentum.sprint4.domain.usecases

import com.qualentum.sprint4.data.repository.ContactsRepository
import com.qualentum.sprint4.domain.mappers.ContactsConverter
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.domain.model.DetailContactModel
import javax.inject.Inject

class ContactsUseCases @Inject constructor(
    private val repository: ContactsRepository
) {
    suspend fun getAllContacts(): List<ContactModel> {
        return ContactsConverter.entityListToModelList(repository.getContacts())
    }

    suspend fun insertContact(contact: DetailContactModel) {
        repository.insertContact(contact)
    }
}
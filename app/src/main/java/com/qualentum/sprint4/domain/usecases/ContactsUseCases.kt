package com.qualentum.sprint4.domain.usecases

import com.qualentum.sprint4.data.repository.ContactsRepository
import com.qualentum.sprint4.domain.mappers.ContactsConverter
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.domain.model.DetailContactModel
import javax.inject.Inject

class ContactsUseCases @Inject constructor(
    private val repository: ContactsRepository
) {
    suspend fun insertContact(contact: DetailContactModel) {
        repository.insertContact(ContactsConverter.detailContactModelToContactEntity(contact))
    }

    suspend fun getDetailContactById(id: Int): DetailContactModel {
        return ContactsConverter.contactEntityToDetailModel(repository.getContactById(id))
    }

    suspend fun getContacts(filter: String?): List<ContactModel> {
        return if (filter == "") {
            ContactsConverter.contactEntityListToModelList(repository.getAllContacts())
        } else {
            ContactsConverter.contactEntityListToModelList(repository.getFilteredContact(filter))
        }
    }

    suspend fun deleteContact(id: Int?) = repository.deleteContact(id)

    suspend fun updateFavouriteContact(id: Int?, isFavourite: Boolean?) {
        repository.updateFavouriteContact(id, isFavourite)
    }

    suspend fun getAllFavouritesContacts(): List<ContactModel> {
        return ContactsConverter.contactEntityListToModelList(repository.getAllFavouritesContacts())
    }
}
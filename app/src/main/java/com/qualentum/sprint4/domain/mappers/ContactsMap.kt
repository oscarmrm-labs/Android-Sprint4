package com.qualentum.sprint4.domain.mappers

import com.qualentum.sprint4.data.entity.ContactEntity
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.domain.model.DetailContactModel

class ContactsConverter {
    companion object {
        fun contactEntityListToModelList(contactsEntity: List<ContactEntity>): List<ContactModel> {
            return contactsEntity.map {
                ContactModel(
                    id = it.id,
                    name = it.firstName,
                    lastName = it.lastName
                )
            }
        }

        fun contactEntityToDetailModel(contactsEntity: ContactEntity): DetailContactModel {
            return DetailContactModel(
                name = contactsEntity.firstName,
                lastName = contactsEntity.lastName,
                dateOfBirth = contactsEntity.dateOfBirth,
                favouriteColorHex = contactsEntity.favouriteColorHex,
                favouriteSport = contactsEntity.favouriteSport,
            )
        }
    }
}
package com.qualentum.sprint4.domain.mappers

import com.qualentum.sprint4.data.entity.ContactEntity
import com.qualentum.sprint4.domain.model.ContactModel

class ContactsConverter{
    companion object {
        fun entityListToModelList(contactsEntity: List<ContactEntity>): List<ContactModel> {
            return contactsEntity.map {
                ContactModel(
                    id = it.id,
                    name = it.firstName,
                    lastName = it.lastName
                )
            }
        }
    }
}
package com.qualentum.sprint4.presentation.add_contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.data.AppDatabase
import com.qualentum.sprint4.data.repository.ContactsRepository
import com.qualentum.sprint4.domain.model.DetailContactModel
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContactViewModel(application: Application) : AndroidViewModel(application) {

    private val contactDao = AppDatabase.getDatabase(application).contactDao()
    private val repository = ContactsRepository(contactDao)
    private val contactsUseCases = ContactsUseCases(repository)

    fun insertContact(contact: DetailContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsUseCases.insertContact(contact)
        }
    }
}
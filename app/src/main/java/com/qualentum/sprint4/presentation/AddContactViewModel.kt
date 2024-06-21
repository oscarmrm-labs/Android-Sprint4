package com.qualentum.sprint4.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.data.AppDatabase
import com.qualentum.sprint4.data.model.Contact
import com.qualentum.sprint4.data.repository.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContactViewModel(application: Application): AndroidViewModel(application) {

    private val contactDao = AppDatabase.getDatabase(application).contactDao()
    private var repository = ContactsRepository(contactDao)

    fun insertContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertContact(contact)
        }
    }
}
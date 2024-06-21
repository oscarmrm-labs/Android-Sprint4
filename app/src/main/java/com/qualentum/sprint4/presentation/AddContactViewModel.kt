package com.qualentum.sprint4.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.data.AppDatabase
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.data.repository.ContactsRepository
import com.qualentum.sprint4.domain.model.DetailContactModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContactViewModel(application: Application) : AndroidViewModel(application) {

    private val contactDao = AppDatabase.getDatabase(application).contactDao()
    private var repository = ContactsRepository(contactDao)

    fun insertContact(contact: DetailContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertContact(contact)
        }
    }
}
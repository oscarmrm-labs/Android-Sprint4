package com.qualentum.sprint4.presentation.contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.data.AppDatabase
import com.qualentum.sprint4.data.model.Contact
import com.qualentum.sprint4.data.repository.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactsViewModel(application: Application): AndroidViewModel(application) {

    private val contactDao = AppDatabase.getDatabase(application).contactDao()
    private var repository = ContactsRepository(contactDao)

    private val loadingMutableState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = loadingMutableState

    private var contactsMutableState: MutableStateFlow<List<Contact>> = MutableStateFlow(ArrayList(emptyList()))
    val contactsState: StateFlow<List<Contact>> = contactsMutableState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableState.value = true
            getAllContacts()
            loadingMutableState.value = false
        }
    }

    private suspend fun getAllContacts() {
        contactsMutableState.value = repository.getContacts()
    }
}
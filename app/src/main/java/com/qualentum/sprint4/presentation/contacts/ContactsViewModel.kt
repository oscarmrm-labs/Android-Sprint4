package com.qualentum.sprint4.presentation.contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.data.AppDatabase
import com.qualentum.sprint4.data.dao.ContactDao
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.data.repository.ContactsRepository
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//class ContactsViewModel(application: Application): AndroidViewModel(application) {
@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactsUseCases: ContactsUseCases
): ViewModel() {

    //private val contactDao = AppDatabase.getDatabase(application).contactDao()
    //private val repository = ContactsRepository(contactDao)
    //private val contactsUseCases = ContactsUseCases(repository)

    private val loadingMutableState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = loadingMutableState

    private var contactsMutableState: MutableStateFlow<List<ContactModel>> = MutableStateFlow(ArrayList(emptyList()))
    val contactsState: StateFlow<List<ContactModel>> = contactsMutableState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableState.value = true
            getAllContacts()
            loadingMutableState.value = false
        }
    }

    private suspend fun getAllContacts() {
        contactsMutableState.value = contactsUseCases.getAllContacts()
    }
}
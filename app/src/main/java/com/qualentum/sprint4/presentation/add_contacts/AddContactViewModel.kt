package com.qualentum.sprint4.presentation.add_contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.domain.model.DetailContactModel
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    val contactsUseCases: ContactsUseCases
): ViewModel() {
    fun insertContact(contact: DetailContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsUseCases.insertContact(contact)
        }
    }
}
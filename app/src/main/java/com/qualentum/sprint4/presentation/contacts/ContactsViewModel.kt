package com.qualentum.sprint4.presentation.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactsUseCases: ContactsUseCases
): ViewModel() {
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

    suspend fun getAllContacts() {
        contactsMutableState.value = contactsUseCases.getAllContacts()
    }
}
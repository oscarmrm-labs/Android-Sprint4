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
) : ViewModel() {
    private val loadingMutableState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = loadingMutableState

    private var contactsMutableState: MutableStateFlow<List<ContactModel>> =
        MutableStateFlow(ArrayList(emptyList()))
    val contactsState: StateFlow<List<ContactModel>> = contactsMutableState

    fun getContacts(filter: String? = "") {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableState.value = true
            val query = "%$filter%"
            contactsMutableState.value = contactsUseCases.getContacts(query)
            loadingMutableState.value = false
        }
    }

    fun deleteContact(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableState.value = true
            contactsUseCases.deleteContact(id)
            loadingMutableState.value = false
        }
    }

    fun updateFavouriteContact(id: Int?, isFavourite: Boolean?) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableState.value = true
            contactsUseCases.updateFavouriteContact(id, isFavourite)
            loadingMutableState.value = false
        }
    }
}
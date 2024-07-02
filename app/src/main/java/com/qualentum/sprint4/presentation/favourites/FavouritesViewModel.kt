package com.qualentum.sprint4.presentation.favourites

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
class FavouritesViewModel @Inject constructor(
    val contactsUseCases: ContactsUseCases
): ViewModel() {

    private val loadingMutableState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = loadingMutableState

    private var favouritesContactsMutableState: MutableStateFlow<List<ContactModel>> = MutableStateFlow(ArrayList(emptyList()))
    val favouritesContactsState: StateFlow<List<ContactModel>> = favouritesContactsMutableState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavouriteContacts()
        }
    }

    suspend fun getAllFavouriteContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableState.value = true
            favouritesContactsMutableState.value = contactsUseCases.getAllFavouritesContacts()
            loadingMutableState.value = false
        }
    }

    suspend fun deleteContact(id: Int?) {
        loadingMutableState.value = true
        contactsUseCases.deleteContact(id)
        loadingMutableState.value = false
    }

    suspend fun updateFavouriteContact(id: Int?, isFavourite: Boolean?) {
        loadingMutableState.value = true
        contactsUseCases.updateFavouriteContact(id, isFavourite)
        loadingMutableState.value = false
    }
}
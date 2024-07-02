package com.qualentum.sprint4.presentation.add_contacts

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.domain.model.DetailContactModel
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import com.qualentum.sprint4.presentation.common.location.ManageLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    val contactsUseCases: ContactsUseCases,
    val manageLocation: ManageLocation
): ViewModel() {
    suspend fun insertContact(contact: DetailContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsUseCases.insertContact(contact)
        }
    }

    suspend fun getUserLocation(context: Context?): String {
        val location = manageLocation.getUserLocation(context!!)
        return if (location != null) {
            "latitude: ${location.latitude}, longitude: ${location.longitude}"
        } else {
            return "No funciona"
        }
    }
}
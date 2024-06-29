package com.qualentum.sprint4.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qualentum.sprint4.domain.model.DetailContactModel
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val contactsUseCases: ContactsUseCases,
    val savedStateHandle: SavedStateHandle
): ViewModel() {
    val TAG = "TAG"

    private val idMutableState = MutableStateFlow(1)
    val idState: StateFlow<Int> = idMutableState

    private val loadingMutableState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = loadingMutableState

    private var contactMutableState: MutableStateFlow<DetailContactModel> = MutableStateFlow(DetailContactModel("", "", "", "", ""))
    val contactState: StateFlow<DetailContactModel> = contactMutableState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableState.value = true
            getDetailContact(idMutableState.value)
            loadingMutableState.value = false
        }
    }
    suspend fun getDetailContact(id: Int = 1) {
        contactMutableState.value = contactsUseCases.getDetailContactById(id)
        Log.i(TAG, "getDetailContact: ${contactMutableState.value}")
    }

    fun setContactId(id: Int) {
        idMutableState.value = id
    }
}
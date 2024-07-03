package com.qualentum.sprint4.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.qualentum.sprint4.domain.model.DetailContactModel
import com.qualentum.sprint4.domain.usecases.ContactsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val contactsUseCases: ContactsUseCases,
    val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val loadingMutableState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = loadingMutableState

    private var contactMutableState: MutableStateFlow<DetailContactModel> = MutableStateFlow(DetailContactModel())
    val contactState: StateFlow<DetailContactModel> = contactMutableState

    suspend fun getDetailContact(id: Int = 1) {
        loadingMutableState.value = true
        contactMutableState.value = contactsUseCases.getDetailContactById(id)
        loadingMutableState.value = false
    }
}
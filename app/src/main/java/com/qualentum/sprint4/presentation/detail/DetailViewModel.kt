package com.qualentum.sprint4.presentation.detail

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
    private val contactsUseCases: ContactsUseCases
): ViewModel() {
    private val loadingMutableState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = loadingMutableState

    private var contactMutableState: MutableStateFlow<DetailContactModel> = MutableStateFlow(DetailContactModel())
    val contactState: StateFlow<DetailContactModel> = contactMutableState

    fun getDetailContact(id: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableState.value = true
            contactMutableState.value = contactsUseCases.getDetailContactById(id)
            loadingMutableState.value = false
        }
    }
}
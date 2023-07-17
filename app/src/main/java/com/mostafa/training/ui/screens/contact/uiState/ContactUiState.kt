package com.mostafa.training.ui.screens.contact.uiState

import com.mostafa.training.data.remote.dto.ContactDTO
import com.mostafa.training.data.remote.dto.FaqsDTO

data class ContactUiState(

    val isLoading: Boolean = false,
    val error: String = "",
    val data: ContactDTO? = null
)

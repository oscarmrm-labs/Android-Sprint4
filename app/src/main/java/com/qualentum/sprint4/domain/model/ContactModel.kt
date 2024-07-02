package com.qualentum.sprint4.domain.model

data class ContactModel(
    val id: Int? = 1,
    val name: String? = "",
    val lastName: String? = "",
    val isFavourite: Boolean? = false
)

data class DetailContactModel(
    val name: String? = "",
    val lastName: String? = "",
    val dateOfBirth: String? = "",
    val favouriteColorHex: String? = "",
    val favouriteSport: String? = "",
    val latitude: String? = "",
    val longitude: String? = "",
)

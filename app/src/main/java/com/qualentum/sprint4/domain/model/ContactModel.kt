package com.qualentum.sprint4.domain.model

data class ContactModel(
    val name: String?,
    val lastName: String?,
)

data class DetailContactModel(
    val name: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    //val favouriteColorHex: String?,
    //val favouriteSport: String?,
)

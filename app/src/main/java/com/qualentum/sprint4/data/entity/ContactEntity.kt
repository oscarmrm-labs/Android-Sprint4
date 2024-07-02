package com.qualentum.sprint4.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RenameColumn
import java.util.Date

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String?,
    @ColumnInfo(name = "favourite_color") val favouriteColorHex: String?,
    @ColumnInfo(name = "favourite_sport") val favouriteSport: String?,
    @ColumnInfo(name = "latitude") val latitude: String?,
    @ColumnInfo(name = "longitude") val longitude: String?,
    @ColumnInfo(name = "is_favourite_contact") val isFavouriteContact: Boolean? = false,

    )
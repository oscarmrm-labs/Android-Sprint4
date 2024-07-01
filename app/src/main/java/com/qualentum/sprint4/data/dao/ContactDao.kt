package com.qualentum.sprint4.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.qualentum.sprint4.data.entity.ContactEntity

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY first_name ASC")
    suspend fun getAllContacts(): List<ContactEntity>

    @Query("SELECT * FROM contacts WHERE id = (:contactId)")
    suspend fun getContactById(contactId: Int): ContactEntity

    @Query("SELECT * FROM contacts WHERE first_name LIKE :filter OR last_name LIKE :filter ORDER BY first_name ASC")
    suspend fun getFilteredContact(filter: String?): List<ContactEntity>

    @Insert
    suspend fun insertContact(vararg contacts: ContactEntity)

    @Query("DELETE FROM contacts WHERE id = :contactId")
    suspend fun deleteContactById(contactId: Int?)

    @Query("UPDATE contacts SET is_favourite_contact = :isFavourite WHERE id = :contactId")
    suspend fun updateFavouriteContact(contactId: Int?, isFavourite: Boolean?)

}
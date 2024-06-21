package com.qualentum.sprint4.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.qualentum.sprint4.data.entity.ContactEntity

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY first_name ASC")
    suspend fun getAllContacts(): List<ContactEntity>

    @Query("SELECT * FROM contacts WHERE id IN (:contactIds)")
    suspend fun loadAllByIds(contactIds: IntArray): List<ContactEntity>

    @Insert
    suspend fun insertContact(vararg contacts: ContactEntity)

    @Delete
    suspend fun delete(contact: ContactEntity)

    // TODO: modifycontact
}
package com.qualentum.sprint4.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qualentum.sprint4.data.room.dao.ContactDao
import com.qualentum.sprint4.data.room.entity.ContactEntity

@Database(
    entities = [
        ContactEntity::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}



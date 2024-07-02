package com.qualentum.sprint4.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qualentum.sprint4.data.dao.ContactDao
import com.qualentum.sprint4.data.entity.ContactEntity

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



package org.banking.simple.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.banking.simple.app.features.auth.data.UserDao
import org.banking.simple.app.features.auth.domain.UserEntity
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.features.dashboard.domain.CardEntity
import org.banking.simple.app.features.dashboard.domain.TransactionHistory


@Database(entities = [UserEntity::class, TransactionHistory::class, CardEntity::class
], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun userDao(): UserDao


}


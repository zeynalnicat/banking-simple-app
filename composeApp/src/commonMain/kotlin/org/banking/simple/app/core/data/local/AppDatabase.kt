package org.banking.simple.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.banking.simple.app.features.auth.data.UserDao
import org.banking.simple.app.features.auth.domain.UserEntity
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.features.dashboard.data.local.TransactionDao
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory


@Database(entities = [UserEntity::class, TransactionHistory::class, CardEntity::class
], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao


}


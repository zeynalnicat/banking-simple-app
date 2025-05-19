package org.banking.simple.app.core

import org.banking.simple.app.features.auth.data.UserDao
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.features.dashboard.data.local.TransactionDao

data class DaoHolder(
    val cardDao: CardDao,
    val userDao: UserDao,
    val transactionDao: TransactionDao
)

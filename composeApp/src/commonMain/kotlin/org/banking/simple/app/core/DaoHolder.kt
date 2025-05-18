package org.banking.simple.app.core

import org.banking.app.features.auth.data.UserDao
import org.banking.simple.app.features.dashboard.data.local.CardDao

data class DaoHolder(
    val cardDao: CardDao,
    val userDao: UserDao
)

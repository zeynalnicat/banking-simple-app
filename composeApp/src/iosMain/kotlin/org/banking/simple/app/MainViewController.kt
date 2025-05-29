package org.banking.simple.app

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import org.banking.simple.app.core.DaoHolder
import org.banking.simple.app.core.data.local.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {

    val cardDao = remember { getDatabaseBuilder().cardDao() }
    val userDao = remember { getDatabaseBuilder().userDao() }
    val transactionDao = remember { getDatabaseBuilder().transactionDao() }

    val daoHolder = remember { DaoHolder(cardDao,userDao,transactionDao)}

    App(daoHolder = daoHolder)
 }
package org.banking.simple.app.features.dashboard.presentation

import org.banking.simple.app.features.dashboard.domain.TransactionHistory

sealed class DashboardIntent {

    data class OnGetTransactionHistory(val userId:Int, val cardId:Int): DashboardIntent()
    data class OnAddTransaction(val transactionHistory: TransactionHistory): DashboardIntent()
}
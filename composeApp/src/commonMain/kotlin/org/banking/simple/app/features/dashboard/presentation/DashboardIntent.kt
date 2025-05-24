package org.banking.simple.app.features.dashboard.presentation

import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory

sealed class DashboardIntent {

    data class OnGetTransactionHistory(val userId:Int, val cardId:Int): DashboardIntent()
    data class OnAddTransaction(val transactionHistory: TransactionHistory): DashboardIntent()
    data class OnGetCards(val userId:Int): DashboardIntent()

}
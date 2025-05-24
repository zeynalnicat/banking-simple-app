package org.banking.simple.app.features.dashboard.presentation

import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory

sealed class DashboardIntent {

    data class OnGetTransactionHistory(val userId:Int): DashboardIntent()
    data class OnGetCards(val userId:Int): DashboardIntent()

}
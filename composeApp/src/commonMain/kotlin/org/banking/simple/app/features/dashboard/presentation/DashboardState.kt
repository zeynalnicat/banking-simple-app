package org.banking.simple.app.features.dashboard.presentation

import org.banking.simple.app.features.dashboard.domain.CardEntity
import org.banking.simple.app.features.dashboard.domain.TransactionHistory

data class DashboardState (
    var name : String = "",
    var transactionHistory: List<TransactionHistory> = emptyList(),
    var cardEntity: CardEntity = CardEntity(0,"",0,0,"",""),

    )


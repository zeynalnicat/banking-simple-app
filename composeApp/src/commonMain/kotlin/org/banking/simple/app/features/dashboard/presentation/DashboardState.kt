package org.banking.simple.app.features.dashboard.presentation

import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory

data class DashboardState (
    var name : String = "",
    var transactionHistory: List<TransactionHistory> = emptyList(),
    var cards: List<CardDTO> = emptyList(),
    val focusedCard: CardDTO = CardDTO(0,0,"","","",0),
    val error : String = ""

    )


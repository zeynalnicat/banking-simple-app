package org.banking.simple.app.features.dashboard.presentation

import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO

data class DashboardState (
    var name : String = "",
    var transactionHistory: List<TransactionDTO> = emptyList(),
    var cards: List<CardDTO> = emptyList(),
    val focusedCard: CardDTO = CardDTO(0,0,"","","",0),
    val error : String = ""

    )


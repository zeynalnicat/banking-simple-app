package org.banking.simple.app.features.transfer.presentation

import org.banking.simple.app.features.dashboard.domain.entities.TransactionType
import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO

data class CardDetailsState(
    var balance: Int = 0,
    var showDialog: Boolean = false,
    var transactionType: String = "",
    var transactionTotal: String = "0",
    var transactionHistory : List<TransactionDTO> = emptyList(),
    val card: CardDTO = CardDTO(0,0,"","","",0),
    val error : String = ""

)

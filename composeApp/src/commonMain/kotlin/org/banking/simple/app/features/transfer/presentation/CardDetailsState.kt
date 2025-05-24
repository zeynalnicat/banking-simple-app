package org.banking.simple.app.features.transfer.presentation

import org.banking.simple.app.features.dashboard.domain.entities.TransactionType

data class CardDetailsState(
    var balance: Int = 0,
    var showDialog: Boolean = false,
    var transactionType: TransactionType = TransactionType.ELECTRICITY

)

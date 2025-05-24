package org.banking.simple.app.features.dashboard.domain.models

import org.banking.simple.app.features.dashboard.domain.entities.TransactionType

data class TransactionDTO(
    var id :Int = 0,
    var cardId : Int,
    var userId: Int,
    var total : Int ,
    var isExpense: Boolean,
    var type: String,
)

package org.banking.simple.app.features.dashboard.domain.mapper

import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO


fun CardEntity.toModel(): CardDTO{
    return CardDTO(
        id = this.id,
        userId = this.userId,
        cardName = this.cardName,
        cardColor = this.cardColor.toString(),
        lastDigits = this.lastDigits,
        deposit = this.balance
    )
}

fun CardDTO.toEntity(): CardEntity{
    return CardEntity(
        id = this.id,
        userId = this.userId,
        cardName = this.cardName,
        cardColor = this.cardColor.toString(),
        lastDigits = this.lastDigits,
        balance = this.deposit
    )
}

fun TransactionDTO.toEntity(): TransactionHistory{
    return TransactionHistory(
        id = this.id,
        cardId = this.cardId,
        userId = this.userId,
        isExpense = this.isExpense,
        total = this.total,
        type = this.type
    )
}

fun TransactionHistory.toModel(): TransactionDTO{
    return TransactionDTO(
        id = this.id,
        cardId = this.cardId,
        userId = this.userId,
        isExpense = this.isExpense,
        total = this.total,
        type = this.type
    )
}
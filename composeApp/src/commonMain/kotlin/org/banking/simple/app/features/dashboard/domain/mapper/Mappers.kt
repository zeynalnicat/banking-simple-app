package org.banking.simple.app.features.dashboard.domain.mapper

import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity


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
package org.banking.simple.app.features.new_card.domain

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity

class AddCardUseCase(private val newCardRepository: NewCardRepository) {

    suspend operator fun invoke(card: CardDTO): Result<Unit>{
        return newCardRepository.createCard(card)
    }
}
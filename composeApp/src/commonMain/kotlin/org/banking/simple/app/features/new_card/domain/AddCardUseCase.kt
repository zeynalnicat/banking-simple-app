package org.banking.simple.app.features.new_card.domain

import org.banking.simple.app.features.dashboard.domain.CardEntity

class AddCardUseCase(private val newCardRepository: NewCardRepository) {
    suspend operator fun invoke(newCardEntity: CardEntity){
        return newCardRepository.createCard(newCardEntity)
    }
}
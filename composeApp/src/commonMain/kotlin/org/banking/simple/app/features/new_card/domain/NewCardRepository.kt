package org.banking.simple.app.features.new_card.domain

import org.banking.simple.app.features.dashboard.domain.CardEntity

interface NewCardRepository {

    suspend fun createCard(cardEntity: CardEntity)
}
package org.banking.simple.app.features.new_card.domain

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity

interface NewCardRepository {

    suspend fun createCard(cardDTO: CardDTO): Result<Unit>
}
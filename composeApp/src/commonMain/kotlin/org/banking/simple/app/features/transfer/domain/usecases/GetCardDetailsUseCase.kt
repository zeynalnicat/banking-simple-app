package org.banking.simple.app.features.transfer.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.transfer.domain.CardDetailsRepository

class GetCardDetailsUseCase(private val repository: CardDetailsRepository) {
    suspend operator fun invoke(userId:Int,cardId:Int): Result<CardDTO>{
        return repository.getDetails(userId,cardId)
    }
}
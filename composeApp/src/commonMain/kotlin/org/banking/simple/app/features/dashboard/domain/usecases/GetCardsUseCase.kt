package org.banking.simple.app.features.dashboard.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.DashboardRepository

class GetCardsUseCase(private val repository: DashboardRepository) {

    suspend operator fun invoke(userId:Int): Result<List<CardDTO>>{
        return repository.getCards(userId)
    }
}
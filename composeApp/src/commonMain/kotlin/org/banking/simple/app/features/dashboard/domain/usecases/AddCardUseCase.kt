package org.banking.simple.app.features.dashboard.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.DashboardRepository

class AddCardUseCase(private val repository: DashboardRepository) {

    suspend operator fun invoke(cardDTO: CardDTO): Result<Unit>{
        return repository.insertCard(cardDTO)
    }
}
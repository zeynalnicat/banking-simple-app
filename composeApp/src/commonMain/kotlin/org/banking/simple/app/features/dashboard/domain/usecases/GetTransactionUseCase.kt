package org.banking.simple.app.features.dashboard.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.DashboardRepository
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory

class GetTransactionUseCase(private val dashboardRepository: DashboardRepository) {

    suspend operator fun invoke(userId:Int,cardId:Int): Result<List<TransactionHistory>>{
        return dashboardRepository.getTransactions(userId,cardId)
    }
}
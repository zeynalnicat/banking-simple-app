package org.banking.simple.app.features.dashboard.domain

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.data.local.TransactionDao

class GetTransactionUseCase(private val dashboardRepository: DashboardRepository) {

    suspend operator fun invoke(userId:Int,cardId:Int): Result<List<TransactionHistory>>{
        return dashboardRepository.getTransactions(userId,cardId)
    }
}
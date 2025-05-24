package org.banking.simple.app.features.dashboard.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.DashboardRepository
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO

class GetTransactionsUseCase(private val dashboardRepository: DashboardRepository) {

    suspend operator fun invoke(userId:Int): Result<List<TransactionDTO>>{
        return dashboardRepository.getTransactions(userId)
    }
}
package org.banking.simple.app.features.dashboard.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.DashboardRepository
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory

class AddTransactionUseCase(private val dashboardRepository: DashboardRepository) {

    suspend operator fun invoke(transactionHistory: TransactionHistory): Result<Unit>{
        return dashboardRepository.insertTransaction(transactionHistory)
    }
}
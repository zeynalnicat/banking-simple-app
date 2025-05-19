package org.banking.simple.app.features.dashboard.domain

import org.banking.simple.app.core.data.Result

class AddTransactionUseCase(private val dashboardRepository: DashboardRepository) {

    suspend operator fun invoke(transactionHistory: TransactionHistory): Result<Unit>{
        return dashboardRepository.insertTransaction(transactionHistory)
    }
}
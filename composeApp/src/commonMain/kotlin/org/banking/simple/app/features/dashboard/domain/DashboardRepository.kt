package org.banking.simple.app.features.dashboard.domain

import org.banking.simple.app.core.data.Result

interface DashboardRepository {

    suspend fun insertTransaction(transactionHistory: TransactionHistory): Result<Unit>

    suspend fun getTransactions(userId:Int,cardId:Int):Result<List<TransactionHistory>>
}
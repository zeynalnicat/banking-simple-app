package org.banking.simple.app.features.dashboard.data

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.data.local.TransactionDao
import org.banking.simple.app.features.dashboard.domain.DashboardRepository
import org.banking.simple.app.features.dashboard.domain.TransactionHistory

class DashboardRepositoryImpl(private val transactionDao: TransactionDao): DashboardRepository {
    override suspend fun insertTransaction(transactionHistory: TransactionHistory): org.banking.simple.app.core.data.Result<Unit> {
        try {
            val response = transactionDao.insertTransaction(transactionHistory)
            if(response!=-1L){
                return Result.Success(Unit)
            }
            return Result.Error("Unknown Error")
        }catch (e:Exception){
            return Result.Error(e.message?: "Unknown Error Occurred")
        }
    }

    override suspend fun getTransactions(
        userId: Int,
        cardId: Int
    ): Result<List<TransactionHistory>> {
        try {
            val response = transactionDao.getTransactions(userId,cardId)
            return Result.Success(response)
        }catch (e: Exception){
            return Result.Error(e.message?: "Unknown Error Occurred")
        }
    }
}
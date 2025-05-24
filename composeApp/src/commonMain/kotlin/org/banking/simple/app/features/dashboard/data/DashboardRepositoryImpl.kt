package org.banking.simple.app.features.dashboard.data

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.features.dashboard.data.local.TransactionDao
import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.dashboard.domain.DashboardRepository
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory
import org.banking.simple.app.features.dashboard.domain.mapper.toEntity
import org.banking.simple.app.features.dashboard.domain.mapper.toModel
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO

class DashboardRepositoryImpl(private val transactionDao: TransactionDao,private val cardDao: CardDao): DashboardRepository {
    override suspend fun insertTransaction(transactionHistory: TransactionHistory): Result<Unit> {
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
    ): Result<List<TransactionDTO>> {
        try {
            val response = transactionDao.getTransactions(userId)
            return Result.Success(response.map { it.toModel() })
        }catch (e: Exception){
            return Result.Error(e.message?: "Unknown Error Occurred")
        }
    }

    override suspend fun getCards(userId: Int): Result<List<CardDTO>> {
         try {
             val response = cardDao.getUserCards(userId)
             return Result.Success(response.map { it.toModel() })
         }catch (e: Exception){
             return Result.Error(e.message?: "Unknown Error Occurred")
         }
    }

    override suspend fun insertCard(cardDTO: CardDTO): Result<Unit> {
        try {
            val response = cardDao.insertCard(cardDTO.toEntity())
            if(response!=-1L){
                return Result.Success(Unit)
            }
            return Result.Error("Unknown Error")
        }catch (e: Exception){
            return Result.Error(e.message?: "Unknown Error Occurred")
        }
    }
}
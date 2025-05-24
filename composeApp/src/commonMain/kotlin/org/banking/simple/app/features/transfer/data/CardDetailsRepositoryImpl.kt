package org.banking.simple.app.features.transfer.data

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.features.dashboard.data.local.TransactionDao
import org.banking.simple.app.features.dashboard.domain.mapper.toEntity
import org.banking.simple.app.features.dashboard.domain.mapper.toModel
import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO
import org.banking.simple.app.features.transfer.domain.CardDetailsRepository

class CardDetailsRepositoryImpl(private val cardDao: CardDao,private val transactionDao: TransactionDao): CardDetailsRepository {


    override suspend fun getDetails(
        userId: Int,
        cardId: Int
    ): Result<CardDTO> {
        try {
            val response = cardDao.getCardDetails(userId,cardId)
            return Result.Success(response.toModel())
        }catch (e:Exception){
            return Result.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun insertTransaction(transactionDTO: TransactionDTO): Result<Unit> {
        try {
            val response =transactionDao.insertTransaction(transactionDTO.toEntity())
            val balance = cardDao.getBalance(transactionDTO.userId,transactionDTO.cardId)
            if(response!=-1L){
                val newBalance = balance - transactionDTO.total
                cardDao.updateBalance(transactionDTO.userId,transactionDTO.cardId,newBalance)
                return Result.Success(Unit)
            }
            return Result.Error("Unknown Error")
        }catch (e:Exception){
            return Result.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun getTransactions(
        userId: Int,
        cardId: Int
    ): Result<List<TransactionDTO>> {
        try {
            val response = transactionDao.getTransactionsDue(userId,cardId)
            return Result.Success(response.map { it.toModel() })
        }catch (e:Exception){
            return Result.Error(e.message ?: "Unknown Error")
        }
    }
}
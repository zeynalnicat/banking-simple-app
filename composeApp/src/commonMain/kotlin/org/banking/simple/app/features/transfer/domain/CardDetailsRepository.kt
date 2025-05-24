package org.banking.simple.app.features.transfer.domain

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO

interface CardDetailsRepository {

    suspend fun getDetails(userId:Int,cardId:Int): Result<CardDTO>

    suspend fun insertTransaction(transactionDTO: TransactionDTO):Result<Unit>

    suspend fun getTransactions(userId: Int,cardId: Int):Result<List<TransactionDTO>>
}
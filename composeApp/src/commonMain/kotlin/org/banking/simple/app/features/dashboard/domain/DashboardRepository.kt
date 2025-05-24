package org.banking.simple.app.features.dashboard.domain

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory

interface DashboardRepository {

    suspend fun insertTransaction(transactionHistory: TransactionHistory): Result<Unit>

    suspend fun getTransactions(userId:Int,cardId:Int):Result<List<TransactionHistory>>

    suspend fun getCards(userId:Int):Result<List<CardDTO>>

    suspend fun insertCard(cardDTO: CardDTO):Result<Unit>
}
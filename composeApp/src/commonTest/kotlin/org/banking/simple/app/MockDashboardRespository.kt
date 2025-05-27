package org.banking.simple.app

import org.banking.simple.app.features.dashboard.domain.DashboardRepository
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory
import org.banking.simple.app.features.dashboard.domain.models.CardDTO
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO

//class MockDashboardRepository : DashboardRepository {
//
//    private val transactions = mutableListOf<TransactionHistory>()
//    private val cards = mutableListOf<CardDTO>()
//
//    override suspend fun insertTransaction(transactionHistory: TransactionHistory): org.banking.simple.app.core.data.Result<Unit> {
//        transactions.add(transactionHistory)
//        return org.banking.simple.app.core.data.Result.Success(Unit)
//    }
//
//
//    override suspend fun getTransactions(userId: Int): org.banking.simple.app.core.data.Result<List<TransactionDTO>> {
//        val filteredTransactions = transactions
//            .filter { it.userId == userId }
//            .map { transactionHistory ->
//
//            }
//        return org.banking.simple.app.core.data.Result.Success(filteredTransactions)
//    }
//
//    override suspend fun getCards(userId: Int): Result<List<CardDTO>> {
//        val filteredCards = cards.filter { it.userId == userId }
//        return Result.Success(filteredCards)
//    }
//
//    override suspend fun insertCard(cardDTO: CardDTO): org.banking.simple.app.core.data.Result<Unit> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun insertCard(cardDTO: CardDTO): Result<Unit> {
//        cards.add(cardDTO)
//        return Result.Success(Unit)
//    }
//}

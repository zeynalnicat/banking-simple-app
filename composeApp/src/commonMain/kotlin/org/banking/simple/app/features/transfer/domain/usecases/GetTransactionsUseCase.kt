package org.banking.simple.app.features.transfer.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO
import org.banking.simple.app.features.transfer.domain.CardDetailsRepository

class GetTransactionsUseCase(private val repository: CardDetailsRepository) {

    suspend operator fun invoke(userId:Int,cardId:Int): Result<List<TransactionDTO>>{
        return repository.getTransactions(userId,cardId)
    }
}
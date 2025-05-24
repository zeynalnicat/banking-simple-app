package org.banking.simple.app.features.transfer.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO
import org.banking.simple.app.features.transfer.domain.CardDetailsRepository

class AddTransactionUseCase(private val repository: CardDetailsRepository) {

    suspend operator fun invoke(transactionDTO: TransactionDTO): Result<Unit>{
        return repository.insertTransaction(transactionDTO)
    }
}
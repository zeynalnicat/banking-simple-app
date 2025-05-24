package org.banking.simple.app.features.transfer.presentation

import org.banking.simple.app.features.dashboard.domain.entities.TransactionType

sealed class CardDetailsIntent {
    data class OnShowDialog(val show: Boolean): CardDetailsIntent()
    data class OnValueChange(val data:String): CardDetailsIntent()
    data class OnSetTransactionType(val transactionType:String): CardDetailsIntent()
    data class OnGetCardDetails(val userId:Int, val cardId:Int): CardDetailsIntent()
    data object OnInsertTransaction: CardDetailsIntent()
    data class OnGetTransactions(val userId:Int, val cardId:Int): CardDetailsIntent()
}
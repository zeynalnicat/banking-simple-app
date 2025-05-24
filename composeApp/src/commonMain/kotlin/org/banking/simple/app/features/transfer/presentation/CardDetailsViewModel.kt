package org.banking.simple.app.features.transfer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.entities.TransactionType
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO
import org.banking.simple.app.features.transfer.domain.usecases.AddTransactionUseCase
import org.banking.simple.app.features.transfer.domain.usecases.GetCardDetailsUseCase
import org.banking.simple.app.features.transfer.domain.usecases.GetTransactionsUseCase

class CardDetailsViewModel(
    private val cardDetailsUseCase: GetCardDetailsUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase

    ):ViewModel() {

    private val _state = MutableStateFlow<CardDetailsState>(CardDetailsState())

    val state : StateFlow<CardDetailsState> = _state.asStateFlow()

    fun onIntent(intent: CardDetailsIntent){
        when(intent){
            is CardDetailsIntent.OnShowDialog -> showDialog(intent.show)
            is CardDetailsIntent.OnValueChange ->setTotalTransaction(intent.data)
            is CardDetailsIntent.OnSetTransactionType -> setTransactionType(intent.transactionType)
            is CardDetailsIntent.OnGetCardDetails ->getCardDetails(userId =intent.userId, cardId = intent.cardId)
            CardDetailsIntent.OnInsertTransaction -> addTransaction()
            is CardDetailsIntent.OnGetTransactions -> getTransactions(intent.userId,intent.cardId)
        }
    }

    private fun showDialog(show: Boolean){
        _state.update { it.copy(showDialog = show) }
    }

    private fun setTotalTransaction( total:String){
        _state.update { it.copy(transactionTotal = total) }
    }

    private fun setTransactionType(transactionType:String){
        _state.update { it.copy(transactionType = transactionType) }
    }

    private fun getCardDetails(userId:Int,cardId:Int){
        viewModelScope.launch{
            when(val result = cardDetailsUseCase.invoke(userId,cardId)){
                is Result.Error -> {_state.update { it.copy(error = result.message) }}
                Result.Loading -> TODO()
                is Result.Success -> {_state.update { it.copy(card = result.data, balance = result.data.deposit) }}
            }
        }
    }

    private fun addTransaction(){
        viewModelScope.launch {
            val card =_state.value.card
            val newBalance = _state.value.balance -_state.value.transactionTotal.toInt()
            val transactionDTO = TransactionDTO(0,card.id,card.userId,_state.value.transactionTotal.toInt(), isExpense = true, type =state.value.transactionType )
            when(val result = addTransactionUseCase(transactionDTO)){
                is Result.Error -> {_state.update { it.copy(error = result.message) }}
                Result.Loading -> TODO()
                is Result.Success -> {
                    _state.update { it.copy(showDialog = false, transactionType = "", balance =newBalance,transactionHistory = _state.value.transactionHistory + transactionDTO) }
                    }
                }
        }
    }

    private fun getTransactions(userId: Int,cardId: Int){
        viewModelScope.launch {

            when(val result = getTransactionsUseCase(userId,cardId)){
                is Result.Error -> {_state.update { it.copy(error = result.message) }}
                Result.Loading -> TODO()
                is Result.Success-> {_state.update { it.copy( transactionHistory =result.data  )}}
            }
        }
    }



}
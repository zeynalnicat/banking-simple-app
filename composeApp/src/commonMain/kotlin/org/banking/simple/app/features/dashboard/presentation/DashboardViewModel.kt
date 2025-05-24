package org.banking.simple.app.features.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.usecases.AddTransactionUseCase
import org.banking.simple.app.features.dashboard.domain.usecases.GetTransactionUseCase
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory
import org.banking.simple.app.features.dashboard.domain.entities.TransactionType
import org.banking.simple.app.features.dashboard.domain.usecases.AddCardUseCase
import org.banking.simple.app.features.dashboard.domain.usecases.GetCardsUseCase


class DashboardViewModel(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getTransactionUseCase: GetTransactionUseCase,
    private val getCardsUseCase: GetCardsUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    fun onIntent(dashboardIntent: DashboardIntent){
       when(dashboardIntent){
           is DashboardIntent.OnAddTransaction -> insert()
           is DashboardIntent.OnGetTransactionHistory -> getTransactionHistory()
           is DashboardIntent.OnGetCards -> getCards(dashboardIntent.userId)

       }
    }

    private fun insert(){
        viewModelScope.launch {
            when(addTransactionUseCase(TransactionHistory(0,1,0,0,false, TransactionType.ELECTRICITY))){
                is Result.Error -> TODO()
                Result.Loading -> TODO()
                is Result.Success<*> -> TODO()
            }
        }
    }

    private fun getTransactionHistory(){
        viewModelScope.launch {
//            when(val result = getTransactionUseCase(state.value.cards.userId,state.value.cardEntity.id)){
//                is Result.Error -> TODO()
//                Result.Loading -> TODO()
//                is Result.Success<List<TransactionHistory>> -> _state.update { state -> state.copy(transactionHistory = emptyList()) }
//            }
        }
    }

    private fun getCards(userId:Int){
        viewModelScope.launch {
            when(val result = getCardsUseCase(userId)){
                is Result.Error -> _state.update { it.copy(error = result.message) }
                Result.Loading -> TODO()
                is Result.Success -> _state.update { it.copy(cards = result.data) }
            }
        }
    }

    private fun insertCard(){

    }
}
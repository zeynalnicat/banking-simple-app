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
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory
import org.banking.simple.app.features.dashboard.domain.entities.TransactionType

import org.banking.simple.app.features.dashboard.domain.usecases.GetCardsUseCase
import org.banking.simple.app.features.dashboard.domain.usecases.GetTransactionsUseCase


class DashboardViewModel(
    private val getTransactionUseCase: GetTransactionsUseCase,
    private val getCardsUseCase: GetCardsUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    fun onIntent(dashboardIntent: DashboardIntent){
       when(dashboardIntent){
           is DashboardIntent.OnGetTransactionHistory -> getTransactionHistory(userId = dashboardIntent.userId)
           is DashboardIntent.OnGetCards -> getCards(dashboardIntent.userId)

       }
    }



    private fun getTransactionHistory(userId:Int){
        viewModelScope.launch {
            when(val result = getTransactionUseCase(userId)){
                is Result.Error -> _state.update { it.copy(error = result.message) }
                Result.Loading -> TODO()
                is Result.Success -> _state.update { state -> state.copy(transactionHistory = result.data) }
            }
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

}
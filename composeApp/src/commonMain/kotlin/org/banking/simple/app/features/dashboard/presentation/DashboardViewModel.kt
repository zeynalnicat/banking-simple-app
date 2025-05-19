package org.banking.simple.app.features.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.AddTransactionUseCase
import org.banking.simple.app.features.dashboard.domain.GetTransactionUseCase
import org.banking.simple.app.features.dashboard.domain.TransactionHistory
import org.banking.simple.app.features.dashboard.domain.TransactionType


class DashboardViewModel(private val addTransactionUseCase: AddTransactionUseCase, private val getTransactionUseCase: GetTransactionUseCase): ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    fun onIntent(dashboardIntent: DashboardIntent){
       when(dashboardIntent){
           is DashboardIntent.OnAddTransaction -> insert()
           is DashboardIntent.OnGetTransactionHistory -> TODO()
       }
    }

    fun insert(){
        viewModelScope.launch {
            when(val result = addTransactionUseCase(TransactionHistory(0,1,0,0,false, TransactionType.ELECTRICITY))){
                is Result.Error -> TODO()
                Result.Loading -> TODO()
                is Result.Success<*> -> TODO()
            }
        }
    }

    fun getTransactionHistory(){
        viewModelScope.launch {
            when(val result = getTransactionUseCase(state.value.cardEntity.userId,state.value.cardEntity.id)){
                is Result.Error -> TODO()
                Result.Loading -> TODO()
                is Result.Success<List<TransactionHistory>> -> _state.update { state -> state.copy(transactionHistory = emptyList()) }
            }
        }
    }
}
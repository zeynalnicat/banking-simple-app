package org.banking.simple.app.features.new_card.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity
import org.banking.simple.app.features.new_card.domain.AddCardUseCase

class NewCardViewModel(private val addCardUseCase: AddCardUseCase) : ViewModel(){


    private val _state = MutableStateFlow(NewCardState())
    val state: StateFlow<NewCardState> = _state.asStateFlow()

    fun onIntent(intent: NewCardIntent){
        when(intent){
            is NewCardIntent.OnSave -> createCard(intent.userId,intent.navigate)
            is NewCardIntent.OnUpdateCardColor -> setCardColor(intent.color)
            is NewCardIntent.OnUpdateCardName -> setCardName(intent.cardName)
            is NewCardIntent.OnUpdateInitialDeposit -> setInitialDeposit(intent.deposit)
        }
    }

    private fun setCardName(name:String){
        _state.update { it.copy(cardName = name) }
    }

    private fun setCardColor(color: String){
        _state.update { it.copy(cardColor = color) }
    }

    private fun setInitialDeposit(deposit:String){
        _state.update { it.copy(initialDeposit = deposit) }
    }

    private fun createCard(userId:Int,nav:()->Unit){
        val card = CardDTO(0,userId,state.value.cardName, deposit = state.value.initialDeposit.toInt(), cardColor = state.value.cardColor, lastDigits = (1000..9999).random().toString())
        viewModelScope.launch {
            when(val result = addCardUseCase(card)){
                is Result.Error -> _state.update { it.copy(error = result.message) }
                Result.Loading -> TODO()
                is Result.Success<*> -> {nav()}
            }
        }
    }



}
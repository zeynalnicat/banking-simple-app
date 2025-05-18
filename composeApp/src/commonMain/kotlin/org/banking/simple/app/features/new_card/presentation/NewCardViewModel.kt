package org.banking.simple.app.features.new_card.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.banking.simple.app.features.dashboard.domain.CardEntity
import org.banking.simple.app.features.new_card.NewCardIntent
import org.banking.simple.app.features.new_card.domain.AddCardUseCase
import org.banking.simple.app.features.new_card.domain.NewCardRepository

class NewCardViewModel(private val addCardUseCase: AddCardUseCase) : ViewModel(){


    private val _state = MutableStateFlow(NewCardState())
    val state: StateFlow<NewCardState> = _state.asStateFlow()

    fun onIntent(intent: NewCardIntent){
        when(intent){
            NewCardIntent.OnSave -> createCard(CardEntity(0,state.value.cardName,0,state.value.initialDeposit.toInt(),state.value.cardColor.toString()))
            is NewCardIntent.OnUpdateCardColor -> setCardColor(intent.color)
            is NewCardIntent.OnUpdateCardName -> setCardName(intent.cardName)
            is NewCardIntent.OnUpdateInitialDeposit -> setInitialDeposit(intent.deposit)
        }
    }

    private fun setCardName(name:String){
        _state.update { it.copy(cardName = name) }
    }

    private fun setCardColor(color: Color){
        _state.update { it.copy(cardColor = color) }
    }

    private fun setInitialDeposit(deposit:String){
        _state.update { it.copy(initialDeposit = deposit) }
    }

    private fun createCard(newCardEntity: CardEntity){
        viewModelScope.launch {
            addCardUseCase(newCardEntity)
        }
    }



}
package org.banking.simple.app.features.pin_entry.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.pin_entry.domain.GetUsernameUseCase
import org.banking.simple.app.features.pin_entry.domain.OnInsertPinUseCase

class EntryViewModel(private val getUsernameUseCase: GetUsernameUseCase,private val enterPinUseCase: OnInsertPinUseCase): ViewModel() {

    private val _state = MutableStateFlow<EntryState>(EntryState())

    val state: StateFlow<EntryState> = _state.asStateFlow()

    fun onIntent(intent: EntryIntent){
        when(intent) {
            EntryIntent.HandleBackPress -> handleBackspace()
            EntryIntent.OnHandleClear -> handleClear()
            is EntryIntent.OnHandleNumberPress -> handleNumberPress(intent.number,intent.navigation)
            EntryIntent.OnHandleShow -> handleShow()
            is EntryIntent.OnFetchUsername -> getUsername(intent.userId)
        }
    }


   private fun handleNumberPress(number: String,nav:()->Unit) {
       _state.update { it.copy(error = "") }
        if (_state.value.currentIndex < 6) {
            val newPin = _state.value.pin.toMutableList()
            newPin[_state.value.currentIndex] = number
            _state.update { it.copy(pin = newPin, currentIndex = _state.value.currentIndex+1) }
        }


       if(_state.value.currentIndex==6){
           enterPin { nav() }
       }

    }

    private fun getUsername(userId:Int){
        viewModelScope.launch {
            val result = getUsernameUseCase(userId)
           when( result){
               is Result.Error -> {_state.update { it.copy(error = result.message) }}
               Result.Loading -> TODO()
               is Result.Success -> { _state.update { it.copy(name = result.data) }}
           }

        }
    }

    private fun enterPin(navigation:()->Unit){
        viewModelScope.launch {
            val result = enterPinUseCase(_state.value.pin.joinToString(""))
            when(result){
                is Result.Error -> {_state.update { it.copy(error = result.message) }}
                Result.Loading -> TODO()
                is Result.Success<*> -> navigation()
            }
        }
    }


    private fun handleBackspace() {
        _state.update { it.copy(error = "") }
        if (_state.value.currentIndex > 0) {
            _state.update { it.copy(currentIndex = _state.value.currentIndex-1) }
            val newPin = _state.value.pin.toMutableList()
            newPin[_state.value.currentIndex] = ""
            _state.update { it.copy(pin = newPin) }
        }
    }

    private fun handleClear() {
        _state.update { it.copy(pin = List(6) { "" }, currentIndex = 0, error = "") }
    }

    private fun handleShow(){
        _state.update { it.copy(showPin = !_state.value.showPin) }
    }


}
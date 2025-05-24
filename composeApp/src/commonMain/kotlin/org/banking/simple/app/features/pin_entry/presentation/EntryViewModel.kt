package org.banking.simple.app.features.pin_entry.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EntryViewModel: ViewModel() {

    private val _state = MutableStateFlow<EntryState>(EntryState())

    val state: StateFlow<EntryState> = _state.asStateFlow()

    fun onIntent(intent: EntryIntent){
        when(intent) {
            EntryIntent.HandleBackPress -> handleBackspace()
            EntryIntent.OnHandleClear -> handleClear()
            is EntryIntent.OnHandleNumberPress -> handleNumberPress(intent.number)
            EntryIntent.OnHandleShow -> handleShow()
        }
    }


   private fun handleNumberPress(number: String) {
        if (_state.value.currentIndex < 6) {
            val newPin = _state.value.pin.toMutableList()
            newPin[_state.value.currentIndex] = number
            _state.update { it.copy(pin = newPin, currentIndex = _state.value.currentIndex+1) }


        }
    }

    private fun handleBackspace() {
        if (_state.value.currentIndex > 0) {
            _state.update { it.copy(currentIndex = _state.value.currentIndex-1) }
            val newPin = _state.value.pin.toMutableList()
            newPin[_state.value.currentIndex] = ""
            _state.update { it.copy(pin = newPin) }
        }
    }

    private fun handleClear() {
        _state.update { it.copy(pin = List(6) { "" }, currentIndex = 0) }
    }

    private fun handleShow(){
        _state.update { it.copy(showPin = !_state.value.showPin) }
    }
}
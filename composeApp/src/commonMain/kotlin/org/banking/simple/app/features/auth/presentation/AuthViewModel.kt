package org.banking.simple.app.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.banking.simple.app.features.auth.domain.AuthUseCase
import org.banking.simple.app.features.auth.domain.UserEntity
import org.banking.simple.app.features.new_card.presentation.NewCardState


class AuthViewModel(private val authUseCase: AuthUseCase): ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun onIntent(authIntent: AuthIntent){
        when(authIntent){
            is AuthIntent.OnSaveName -> saveName(authIntent.name)
            is AuthIntent.OnSavePin -> savePin(authIntent.pin)
            AuthIntent.OnSubmit -> TODO()
        }
    }

    private fun saveName(name: String){
        _state.update { it.copy(name=name) }
    }

    private fun savePin(pin:String){
        _state.update { it.copy(pin=pin) }
    }

    private fun submit(){
        viewModelScope.launch {
            authUseCase(UserEntity(0,state.value.name,state.value.pin))
        }

    }

}
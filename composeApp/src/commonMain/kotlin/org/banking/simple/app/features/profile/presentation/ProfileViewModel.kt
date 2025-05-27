package org.banking.simple.app.features.profile.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.profile.domain.usecases.GetUserUseCase
import org.banking.simple.app.features.profile.domain.usecases.UpdateNameUseCase

class ProfileViewModel(private val getUserUseCase: GetUserUseCase,private val updateNameUseCase: UpdateNameUseCase): ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState())

    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun onIntent(intent: ProfileIntent){
        when(intent){
            is ProfileIntent.OnGetUser -> getUser(intent.userId)
            is ProfileIntent.OnChangeUsername -> changeUsername(intent.username)
            ProfileIntent.OnShowBottomSheet -> showBottomSheet()
            ProfileIntent.OnSaveToggle -> onHandleSave()
        }
    }

    private fun getUser(userId:Int){
        viewModelScope.launch {
            when(val result = getUserUseCase(userId)){
                is Result.Error -> {_state.update { it.copy(error = result.message) }}
                Result.Loading -> TODO()
                is Result.Success -> {_state.update { it.copy(id = userId, name = result.data.name, changedName = result.data.name) }}
            }
        }
    }

    private fun showBottomSheet(){
        _state.update { it.copy(showBottomSheet = !_state.value.showBottomSheet,changedName = _state.value.name) }
    }

    private fun changeUsername(username:String){
        _state.update { it.copy(changedName =  username) }
    }

    private fun onHandleSave(){
        viewModelScope.launch {
            when(val result = updateNameUseCase(_state.value.id,_state.value.changedName)){
                is Result.Error -> {_state.update { it.copy(error = result.message, showBottomSheet = false, changedName = _state.value.name) }}
                Result.Loading -> TODO()
                is Result.Success -> {_state.update { it.copy(name = _state.value.changedName,  showBottomSheet = false) }}
            }
        }
    }
}
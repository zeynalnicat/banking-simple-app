package org.banking.simple.app.features.auth.presentation

sealed class AuthIntent {

    data class OnSaveName( val name:String): AuthIntent()
    data class OnSavePin( val pin:String): AuthIntent()
    object OnSubmit: AuthIntent()
}
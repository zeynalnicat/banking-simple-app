package org.banking.simple.app.features.auth.presentation


data class AuthState (
    val name : String = "",
    val pin: String = "",
    val error: String = "",
)
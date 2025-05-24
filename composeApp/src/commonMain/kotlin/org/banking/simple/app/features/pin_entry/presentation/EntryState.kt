package org.banking.simple.app.features.pin_entry.presentation

data class EntryState(
    val pin : List<String> = List(6) { "" },
    val currentIndex: Int = 0,
    val showPin: Boolean = false,
    val name: String = "",
    val error: String = "",
)
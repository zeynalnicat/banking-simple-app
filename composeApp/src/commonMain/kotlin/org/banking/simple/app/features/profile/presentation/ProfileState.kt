package org.banking.simple.app.features.profile.presentation

data class ProfileState(
    val id:Int = 0,
    val name:String = "",
    val error: String = ""
)
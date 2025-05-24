package org.banking.simple.app.features.profile.presentation

sealed class ProfileIntent {

    data class OnGetUser(val userId: Int): ProfileIntent()
}
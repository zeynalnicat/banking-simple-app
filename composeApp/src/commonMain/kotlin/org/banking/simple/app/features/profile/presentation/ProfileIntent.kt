package org.banking.simple.app.features.profile.presentation

sealed class ProfileIntent {

    data class OnGetUser(val userId: Int): ProfileIntent()
    data class OnChangeUsername(val username:String): ProfileIntent()
    data object OnShowBottomSheet: ProfileIntent()
    data object OnSaveToggle: ProfileIntent()
}
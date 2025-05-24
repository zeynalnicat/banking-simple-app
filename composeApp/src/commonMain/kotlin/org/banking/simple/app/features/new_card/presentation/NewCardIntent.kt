package org.banking.simple.app.features.new_card.presentation

import androidx.compose.ui.graphics.Color

sealed class NewCardIntent {
    data class OnUpdateCardColor(val color: String): NewCardIntent()
    data class OnUpdateCardName(val cardName:String): NewCardIntent()
    data class OnUpdateInitialDeposit(val deposit: String ): NewCardIntent()
    data class OnSave(val userId:Int,val navigate:()->Unit): NewCardIntent()
}
package org.banking.simple.app.features.new_card.presentation

import androidx.compose.ui.graphics.Color
import org.banking.simple.app.features.shared.ui.colors.AppColors

data class NewCardState(
    var cardName : String = "",
    var cardColor : Color = AppColors.blue,
    var initialDeposit: String = "0",
)

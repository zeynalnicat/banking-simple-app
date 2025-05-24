package org.banking.simple.app.features.new_card.presentation


data class NewCardState(
    var cardName : String = "",
    var cardColor : String = "18446656211563577344",
    var initialDeposit: String = "0",
    var error: String = ""
)

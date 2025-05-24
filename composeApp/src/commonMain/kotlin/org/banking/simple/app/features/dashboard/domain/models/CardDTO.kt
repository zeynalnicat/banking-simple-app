package org.banking.simple.app.features.dashboard.domain.models

data class CardDTO (
    val id:Int,
    val userId:Int,
    val cardName:String,
    var lastDigits:String,
    var cardColor: String,
    var deposit:Int,
)
package org.banking.simple.app.core

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Dashboard : Screen("dashboard")
    object Transfer : Screen("transfer/{cardId}") {
        fun withCardId(cardId: Int) = "transfer/$cardId"
    }
    object NewCard : Screen("newScreen")
    object Profile: Screen("profile")
    object Entry : Screen("entry")
}

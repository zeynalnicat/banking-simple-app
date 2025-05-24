package org.banking.simple.app.features.transfer.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssuredWorkload
import androidx.compose.material.icons.filled.EMobiledata
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.ui.graphics.vector.ImageVector


object OperationItems {

    val paymentLists = listOf(
        OperationItem(Icons.Default.Lightbulb,"Electricity"),
        OperationItem(Icons.Default.AssuredWorkload,"Assurance"),
        OperationItem(Icons.Default.EMobiledata,"Mobile Credit"),
        OperationItem(Icons.Default.ShoppingCart,"Merchant"),
        OperationItem(Icons.Default.Fastfood,"Food"),
        OperationItem(Icons.Default.Healing,"Health")
    )

    val operationItems = listOf(
        OperationItem(Icons.Default.Wallet, "Top Up"),
        OperationItem(Icons.Default.Send, "Send"),
    )

}



data class OperationItem (
    var icon : ImageVector,
    var name: String,
)
package org.banking.simple.app.features.dashboard.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.banking.simple.app.features.shared.ui.components.DSizedBox
import org.banking.simple.app.core.Screen

@Composable
fun HeaderSection(navController:NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text("Bank", color = Color.Black, fontSize = 20.sp)
            DSizedBox.eightW()
            Text("Banktut", color = Color(0xFF5D8DF6), fontSize = 20.sp)
        }
        Icon(
            imageVector = Icons.Default.AddCard,
            contentDescription = "Add Card",
            tint = Color.Black,
            modifier = Modifier.clickable {
                navController.navigate(Screen.NewCard.route )
            }
        )
    }
}
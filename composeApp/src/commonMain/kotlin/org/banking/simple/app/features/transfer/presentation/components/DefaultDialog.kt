package org.banking.simple.app.features.transfer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.banking.simple.app.features.dashboard.presentation.DashboardState
import org.banking.simple.app.features.new_card.presentation.NewCardIntent
import org.banking.simple.app.features.shared.ui.components.BankButton
import org.banking.simple.app.features.shared.ui.components.BankTextField
import org.banking.simple.app.features.transfer.presentation.CardDetailsState

@Composable
fun TransactionDefaultDialog(onDismiss: () -> Unit, onConfirm: () -> Unit,transactionType:String,value:String, onValueChange: (String)->Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.1f))
            .clickable(onClick = onDismiss)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp)
                .clickable(enabled = false) {}
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Transaction: $transactionType ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                BankTextField(
                    value = value.toString(),
                    onValueChange ={onValueChange(it)},
                    placeholder = "Total ",
                    label = "Total Transaction")
                Spacer(modifier = Modifier.height(24.dp))
                Row {

                    BankButton(onClick = onConfirm, "Confirm")

                }
            }
        }
    }
}

package org.banking.simple.app.features.dashboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatIndentDecrease
import androidx.compose.material.icons.filled.Maximize
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.NotInterested

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.banking.simple.app.features.dashboard.domain.models.TransactionDTO
import org.banking.simple.app.features.shared.ui.components.DSizedBox
import org.banking.simple.app.features.transfer.data.OperationItems

@Composable
fun TransactionItem(transaction:TransactionDTO) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF181F3A), shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            val operationIcon = OperationItems.paymentLists.find { it.name == transaction.type } ?: OperationItems.operationItems.find { it.name == transaction.type }

            Icon(operationIcon?.icon?:Icons.Default.NotInterested, contentDescription = transaction.type, tint = Color.White)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(transaction.type, color = Color.Black, fontSize = 14.sp)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            if(transaction.isExpense){
                Text("-", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.W600)
                Text("\$${transaction.total}", color = Color.Red, fontSize = 14.sp, fontWeight = FontWeight.W600)

            }
            else{
                Text("+", color = Color.Green.copy(green = 0.8f), fontSize = 18.sp, fontWeight = FontWeight.W600)
                Text("\$${transaction.total}", color = Color.Green.copy(green = 0.8f), fontSize = 14.sp, fontWeight = FontWeight.W600)
            }

        }

    }
}
package org.banking.simple.app.features.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import org.banking.simple.app.features.dashboard.presentation.ActivityItem
import org.banking.simple.app.features.shared.ui.components.DSizedBox

@Composable
fun RecentActivities(transactions:List<TransactionDTO>) {
    Column(modifier = Modifier.padding(top=16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Recent Activities", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))
    if(transactions.isEmpty()){
        Text(
            text = "No transactions found",
            color = Color.Gray.copy(alpha = 0.8f)
        )
    }else{


            LazyColumn {
                items(5) {
                    ActivityItem()
                    DSizedBox.eightH()
                }
            }
        }
    }

}
package org.banking.simple.app.features.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.banking.simple.app.features.dashboard.presentation.components.CardSection
import org.banking.simple.app.features.dashboard.presentation.components.HeaderSection
import org.banking.simple.app.features.dashboard.presentation.components.RecentActivities


@Composable

fun DashboardScreen(navController: NavController){
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding( 16.dp)
        ) {
            HeaderSection(navController)
            CardSection(name = "Nijat Zeynalli", lastDigits = "0421", navController = navController)
            RecentActivities()
        }
    }

}





@Composable
fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionItem(Icons.Default.Send, "Sent")
        ActionItem(Icons.Default.ArrowDownward, "Receive")
        ActionItem(Icons.Default.CloudUpload, "Topup")
        ActionItem(Icons.Default.CropFree, "Payment")
    }
}

@Composable
fun ActionItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(Color(0xFF181F3A), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, color = Color.White, fontSize = 12.sp)
    }
}



@Composable
fun ActivityItem() {
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
            Icon(Icons.Default.Restaurant, contentDescription = null, tint = Color.White)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("Food", color = Color.Black, fontSize = 14.sp)
            Text("15 Oct 2020", color = Color.Black.copy(alpha = 0.6f), fontSize = 12.sp)
        }
        Text("- \$ 40,00", color = Color.Black, fontSize = 14.sp)
    }
}

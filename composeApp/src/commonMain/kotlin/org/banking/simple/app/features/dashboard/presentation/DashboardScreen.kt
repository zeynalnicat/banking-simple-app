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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cmppreference.LocalPreference
import com.example.cmppreference.LocalPreferenceProvider
import org.banking.simple.app.features.dashboard.data.DashboardRepositoryImpl
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.features.dashboard.data.local.TransactionDao
import org.banking.simple.app.features.dashboard.domain.usecases.AddTransactionUseCase

import org.banking.simple.app.features.dashboard.domain.usecases.GetCardsUseCase
import org.banking.simple.app.features.dashboard.domain.usecases.GetTransactionsUseCase

import org.banking.simple.app.features.dashboard.presentation.components.HeaderSection
import org.banking.simple.app.features.dashboard.presentation.components.HorizontalCardList
import org.banking.simple.app.features.dashboard.presentation.components.RecentActivities
import org.banking.simple.app.features.shared.ui.components.DSizedBox


@Composable

fun DashboardScreen(navController: NavController,transactionDao: TransactionDao,cardDao: CardDao){
    val repoImpl = DashboardRepositoryImpl(transactionDao,cardDao)
    val addTransactionUseCase = AddTransactionUseCase(repoImpl)
    val getTransactionUseCase =  GetTransactionsUseCase(repoImpl)
    val getCardsUseCase = GetCardsUseCase(repoImpl)

    val viewModel = viewModel{ DashboardViewModel(addTransactionUseCase,
        getTransactionUseCase,getCardsUseCase) }
    val state = viewModel.state.collectAsState().value
    LocalPreferenceProvider {
        val preference = LocalPreference.current
        LaunchedEffect(Unit) {
            viewModel.onIntent(DashboardIntent.OnGetCards(preference.getInt("userId",-1)))
        }

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize().padding(16.dp)
            ) {
                HeaderSection(navController)
                HorizontalCardList(cards = state.cards, navController = navController)
                DSizedBox.twentyFourH()
                RecentActivities(state.transactionHistory)
            }
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

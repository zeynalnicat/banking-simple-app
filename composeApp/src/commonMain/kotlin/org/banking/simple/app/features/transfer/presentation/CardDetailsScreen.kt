package org.banking.simple.app.features.transfer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssuredWorkload
import androidx.compose.material.icons.filled.EMobiledata
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cmppreference.LocalPreference
import com.example.cmppreference.LocalPreferenceProvider
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.features.dashboard.data.local.TransactionDao
import org.banking.simple.app.features.dashboard.presentation.components.RecentActivities
import org.banking.simple.app.features.shared.ui.colors.AppColors
import org.banking.simple.app.features.shared.ui.components.DSizedBox
import org.banking.simple.app.features.shared.ui.components.TopBar
import org.banking.simple.app.features.transfer.data.CardDetailsRepositoryImpl
import org.banking.simple.app.features.transfer.data.OperationItems.operationItems
import org.banking.simple.app.features.transfer.data.OperationItems.paymentLists
import org.banking.simple.app.features.transfer.domain.CardDetailsRepository
import org.banking.simple.app.features.transfer.domain.usecases.AddTransactionUseCase
import org.banking.simple.app.features.transfer.domain.usecases.GetCardDetailsUseCase
import org.banking.simple.app.features.transfer.domain.usecases.GetTransactionsUseCase

import org.banking.simple.app.features.transfer.presentation.components.TransactionDefaultDialog


@Composable
fun CardDetailsScreen(navController: NavController, cardDao: CardDao, transactionDao: TransactionDao, cardId: Int) {

    val repository = CardDetailsRepositoryImpl(cardDao,transactionDao)
    val getCardDetailsUseCase = GetCardDetailsUseCase(repository)
    val insertTransactionUseCase = AddTransactionUseCase(repository)
    val getTransactionsUseCase = GetTransactionsUseCase(repository)
    val viewModel = viewModel{ CardDetailsViewModel(getCardDetailsUseCase,insertTransactionUseCase,getTransactionsUseCase) }
    val state = viewModel.state.collectAsState().value

    LocalPreferenceProvider {
        val preference = LocalPreference.current

        LaunchedEffect(Unit) {
        viewModel.onIntent(CardDetailsIntent.OnGetCardDetails(userId = preference.getInt("userId",-1), cardId = cardId))
        viewModel.onIntent(CardDetailsIntent.OnGetTransactions(userId = preference.getInt("userId",-1), cardId = cardId))
    }

    Scaffold(
        topBar = {
            TopBar(navController)
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .background(AppColors.primary)
                    .align(Alignment.TopStart)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Available Balance:", color = Color.Gray)
                    DSizedBox.eightH()
                    Text("$${state.balance}", fontSize = 40.sp, color = Color.White)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 200.dp, start = 8.dp, end = 8.dp)
            ) {
                if (state.showDialog) {
                    TransactionDefaultDialog(
                        onDismiss = { viewModel.onIntent(CardDetailsIntent.OnShowDialog(false)) },
                        onConfirm = { viewModel.onIntent(CardDetailsIntent.OnInsertTransaction) },
                        transactionType = state.transactionType,
                        value = state.transactionTotal,
                        onValueChange = { it ->
                            viewModel.onIntent(
                                CardDetailsIntent.OnValueChange(
                                    it
                                )
                            )
                        }
                    )
                }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        operationItems.forEach { item ->
                            Column(
                                modifier = Modifier.padding(vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name,
                                    modifier = Modifier.size(32.dp),
                                    tint = AppColors.primary
                                )
                                DSizedBox.eightH()
                                Text(text = item.name)
                            }
                        }
                    }
                }

                DSizedBox.twentyFourH()
                Spacer(modifier = Modifier.width(16.dp))
                Text("Payment List", fontSize = 18.sp, fontWeight = FontWeight.W600)
                DSizedBox.sixteenH()
                Column {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(paymentLists.size) { index ->
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.fillMaxWidth()
                                    .clickable {
                                        viewModel.onIntent(CardDetailsIntent.OnShowDialog(true))
                                        viewModel.onIntent(
                                            CardDetailsIntent.OnSetTransactionType(
                                                paymentLists[index].name
                                            )
                                        )
                                    }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Icon(
                                        imageVector = paymentLists[index].icon,
                                        contentDescription = paymentLists[index].name,
                                        modifier = Modifier.size(24.dp),
                                        tint = AppColors.primary
                                    )
                                    DSizedBox.sixteenW()
                                    Text(text = paymentLists[index].name)
                                }
                            }
                        }
                    }
                }
                DSizedBox.sixteenH()
                RecentActivities(state.transactionHistory)


            }
        }
    }

    }

    }


package org.banking.simple.app.features.new_card.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.features.dashboard.presentation.components.CardSection
import org.banking.simple.app.features.new_card.NewCardIntent
import org.banking.simple.app.features.new_card.data.NewCardRepositoryImpl
import org.banking.simple.app.features.new_card.domain.AddCardUseCase
import org.banking.simple.app.features.shared.ui.colors.AppColors
import org.banking.simple.app.features.shared.ui.components.BankButton
import org.banking.simple.app.features.shared.ui.components.BankTextField

import org.banking.simple.app.features.shared.ui.components.DSizedBox
import org.banking.simple.app.features.shared.ui.components.TopBar


@Composable
fun NewCardScreen(navController: NavController,cardDao: CardDao) {


    val viewModel = viewModel { NewCardViewModel(AddCardUseCase(NewCardRepositoryImpl(cardDao))) }
    val state = viewModel.state.collectAsState().value


    val coloredItems = listOf(AppColors.blue, AppColors.pink, AppColors.yellow)

    Scaffold(
        topBar = {
            TopBar(navController, title = "Add new card")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            CardSection(
                name = if(state.cardName.isEmpty()) "Card Name" else state.cardName,
                lastDigits = "\u2022\u2022\u2022\u2022",
                cardColor = state.cardColor
            )
            DSizedBox.sixteenH()
            Text("Card Color")
            DSizedBox.eightH()
            LazyRow {
                items(coloredItems.size) { index ->
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(32.dp),
                        border = if (state.cardColor == coloredItems[index])
                            BorderStroke(width = 3.dp, color = Color.White)
                        else null,
                        onClick = { viewModel.onIntent(NewCardIntent.OnUpdateCardColor(coloredItems[index])) }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(coloredItems[index])
                        )
                    }
                }
            }

            DSizedBox.sixteenH()
            BankTextField(value = state.cardName, onValueChange = {viewModel.onIntent(NewCardIntent.OnUpdateCardName(it))} , placeholder = "Enter card name", label = "Card Name" )
            DSizedBox.sixteenH()
            BankTextField(value = state.initialDeposit , onValueChange ={ viewModel.onIntent(
                NewCardIntent.OnUpdateInitialDeposit(it))} , placeholder = "Initial Deposit", label = "Initial Deposit")

            DSizedBox.twentyFourH()

            BankButton({viewModel.onIntent(NewCardIntent.OnSave)},"Save")
        }
    }
}


data class CardColoredItems (
    var color : Color,
    var isSelected : Boolean = false
)
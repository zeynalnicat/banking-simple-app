package org.banking.simple.app.features.new_card.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import org.banking.simple.app.features.dashboard.presentation.components.CardSection
import org.banking.simple.app.features.new_card.NewCardIntent
import org.banking.simple.app.features.new_card.data.NewCardRepositoryImpl
import org.banking.simple.app.features.new_card.domain.AddCardUseCase
import org.banking.simple.app.features.new_card.domain.NewCardRepository
import org.banking.simple.app.features.new_card.presentation.components.DTextField
import org.banking.simple.app.features.shared.ui.colors.AppColors
import org.banking.simple.app.features.shared.ui.components.DSizedBox
import org.banking.simple.app.features.shared.ui.components.TopBar


@Composable
fun NewCardScreen(navController: NavController) {


    val viewModel = viewModel { NewCardViewModel(AddCardUseCase(NewCardRepository(null))) }
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
            DTextField(value = state.cardName, onValueChanged = {viewModel.onIntent(NewCardIntent.OnUpdateCardName(it))} , placeHolder = "Enter card name", label = "Card Name" )
            DSizedBox.sixteenH()
            DTextField(value = state.initialDeposit , onValueChanged ={ viewModel.onIntent(
                NewCardIntent.OnUpdateInitialDeposit(it))} , placeHolder = "Initial Deposit", label = "Initial Deposit")

            DSizedBox.twentyFourH()

            Button(
                onClick = {},
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2567F9)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}


data class CardColoredItems (
    var color : Color,
    var isSelected : Boolean = false
)
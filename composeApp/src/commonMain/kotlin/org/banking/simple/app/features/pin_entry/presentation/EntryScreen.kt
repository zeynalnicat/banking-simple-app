package org.banking.simple.app.features.pin_entry.presentation

import org.banking.simple.app.features.pin_entry.presentation.components.ActionButton
import org.banking.simple.app.features.pin_entry.presentation.components.NumberButton



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cmppreference.LocalPreference
import com.example.cmppreference.LocalPreferenceProvider
import org.banking.simple.app.core.Screen
import org.banking.simple.app.features.auth.data.UserDao
import org.banking.simple.app.features.pin_entry.data.EntryRepositoryImpl
import org.banking.simple.app.features.pin_entry.domain.GetUsernameUseCase
import org.banking.simple.app.features.pin_entry.domain.OnInsertPinUseCase

@Composable
fun BankingPinScreen(
    userDao:UserDao,
    navController:NavController
) {


    LocalPreferenceProvider {
     val preference = LocalPreference.current
    val repository = EntryRepositoryImpl(userDao)
     val getUsernameUseCase = GetUsernameUseCase(repository)
        val insertPinUseCase = OnInsertPinUseCase(repository)
    val viewModel = viewModel { EntryViewModel(getUsernameUseCase,insertPinUseCase) }
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(EntryIntent.OnFetchUsername(preference.getInt("userId",-1)))
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1976D2),
            Color(0xFF1565C0)
        )
    )



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 48.dp)
                ) {
                    Text(
                        text = "Welcome",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.value.name,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    state.value.pin.forEachIndexed { index, digit ->
                        Card(
                            modifier = Modifier.size(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (index == state.value.currentIndex) {
                                    Color.White.copy(alpha = 0.2f)
                                } else if (digit.isNotEmpty()) {
                                    Color.White.copy(alpha = 0.1f)
                                } else {
                                    Color.White.copy(alpha = 0.05f)
                                }
                            ),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (index == state.value.currentIndex) Color.White
                                else Color.White.copy(alpha = 0.3f)
                            )
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = if (state.value.showPin) digit else if (digit.isNotEmpty()) "•" else "",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    IconButton(
                        onClick = { viewModel.onIntent(EntryIntent.OnHandleShow) },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                Color.White.copy(alpha = 0.1f),
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = if (state.value.showPin) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (state.value.showPin) "Hide PIN" else "Show PIN",
                            tint = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }

                if(state.value.error.isNotEmpty()){
                   Text(
                       text = state.value.error,
                       color = Color.Red,
                       fontSize = 12.sp,
                       textAlign = TextAlign.Center,
                       modifier = Modifier.padding(horizontal = 32.dp)
                   )

                }

                Text(
                    text = "Enter your 6-digit PIN to access your account securely",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.1f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    for (row in 0..2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (col in 1..3) {
                                val number = (row * 3 + col).toString()
                                NumberButton(
                                    number = number,
                                    onClick = { viewModel.onIntent(EntryIntent.OnHandleNumberPress(number,{navController.navigate(
                                        Screen.Dashboard.route){popUpTo(Screen.Entry.route ) { inclusive = true}}})) }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ActionButton(
                            text = "Clear",
                            onClick = {viewModel.onIntent(EntryIntent.OnHandleClear) },
                            color = Color(0xFFE53E3E)
                        )

                        NumberButton(
                            number = "0",
                            onClick = { viewModel.onIntent(EntryIntent.OnHandleNumberPress("0",{navController.navigate(
                                Screen.Dashboard.route){popUpTo(Screen.Entry.route ) { inclusive = true}}})) }
                        )

                        ActionButton(
                            text = "⌫",
                            onClick = { viewModel.onIntent(EntryIntent.HandleBackPress) },
                            color = Color(0xFFFF8C00)
                        )
                    }
                }
            }
        }
    }
    }
}




package org.banking.simple.app.features.auth.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cmppreference.LocalPreference
import com.example.cmppreference.LocalPreferenceProvider
import org.banking.simple.app.core.Screen
import org.banking.simple.app.features.auth.data.UserDao
import org.banking.simple.app.features.auth.data.AuthRepositoryImpl
import org.banking.simple.app.features.auth.domain.AuthUseCase
import org.banking.simple.app.features.shared.ui.colors.AppColors
import org.banking.simple.app.features.shared.ui.components.BankButton
import org.banking.simple.app.features.shared.ui.components.BankTextField

@Composable
fun AuthScreen(userDao: UserDao, navController: NavController) {
    val viewModel = viewModel { AuthViewModel(AuthUseCase(AuthRepositoryImpl(userDao))) }
    val state = viewModel.state.collectAsState().value

    LocalPreferenceProvider {
        val preference = LocalPreference.current
        var pinVisible by remember { mutableStateOf(false) }

        if(preference.getString("username")?.isNotEmpty() == true){
            navController.navigate(Screen.Entry.route)
        }else{
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Text(
                            text = "Welcome to Simple Banking",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.primary
                        )

                        Text(
                            text = "Sign in to continue",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )

                        BankTextField(
                            value = state.name,
                            onValueChange = { viewModel.onIntent(AuthIntent.OnSaveName(it)) },
                            label = "Username",
                            placeholder = "Enter your username",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Username Icon",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        BankTextField(
                            value = state.pin,
                            onValueChange = { viewModel.onIntent(AuthIntent.OnSavePin(it)) },
                            label = "PIN",
                            placeholder = "Enter your PIN",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "PIN Icon",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = { pinVisible = !pinVisible }) {
                                    Icon(
                                        imageVector = if (pinVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = if (pinVisible) "Hide PIN" else "Show PIN"
                                    )
                                }
                            },
                            visualTransformation = if (pinVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword,
                                imeAction = ImeAction.Done
                            ),
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        BankButton(
                            onClick = {
                                viewModel.onIntent(
                                    AuthIntent.OnSubmit(
                                        navigate = {
                                            navController.navigate(Screen.Dashboard.route) {
                                                popUpTo(Screen.Auth.route) { inclusive = true }
                                            }
                                        }
                                    )
                                )

                                preference.put("username",state.name)
                            },
                            text = "Sign In",
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        TextButton(
                            onClick = { },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                "Forgot PIN?",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Text(
                        text = "Simple Banking App v1.0",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp)
                    )
                }
            }
        }
        }


}
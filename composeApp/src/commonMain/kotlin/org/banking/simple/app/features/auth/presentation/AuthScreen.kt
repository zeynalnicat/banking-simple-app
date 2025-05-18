package org.banking.simple.app.features.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.banking.app.features.auth.data.UserDao
import org.banking.simple.app.features.auth.data.AuthRepositoryImpl
import org.banking.simple.app.features.auth.domain.AuthUseCase
import org.banking.simple.app.features.shared.ui.components.DButton
import org.banking.simple.app.features.shared.ui.components.DTextField
import org.banking.simple.app.features.shared.ui.components.DSizedBox


@Composable
fun AuthScreen(userDao: UserDao){

    val viewModel = viewModel { AuthViewModel(AuthUseCase(AuthRepositoryImpl(userDao))) }
    val state = viewModel.state.collectAsState().value

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DTextField(value = state.name, onValueChanged = { viewModel.onIntent(AuthIntent.OnSaveName(it))}, label = "Your username", placeHolder = "Enter your credential")
            DSizedBox.sixteenH()
            DTextField(value = state.pin, onValueChanged = {viewModel.onIntent(AuthIntent.OnSavePin(it))}, label = "Your pin", placeHolder = "Enter your pin")
            DSizedBox.twentyFourH()
            DButton({viewModel.onIntent(AuthIntent.OnSubmit)},"Submit")

        }
    }
}

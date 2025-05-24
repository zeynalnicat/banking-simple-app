package org.banking.simple.app.features.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cmppreference.LocalPreference
import com.example.cmppreference.LocalPreferenceProvider
import org.banking.app.features.profile.presentation.components.ProfileMenuItem
import org.banking.simple.app.features.auth.data.UserDao
import org.banking.simple.app.features.profile.data.ProfileRepositoryImpl
import org.banking.simple.app.features.profile.domain.usecases.GetUserUseCase
import org.banking.simple.app.features.shared.ui.colors.AppColors

@Composable
fun ProfileScreen(userDao: UserDao) {
    val iconBlue = Color(0xFF5B9EFC)

    val repository = ProfileRepositoryImpl(userDao)
    val getUserUseCase = GetUserUseCase(repository)
    val viewModel = viewModel{ ProfileViewModel(getUserUseCase = getUserUseCase) }
    val state = viewModel.state.collectAsState().value

    LocalPreferenceProvider {
        val preference = LocalPreference.current
        LaunchedEffect(Unit) {
            viewModel.onIntent(ProfileIntent.OnGetUser(preference.getInt("userId",-1)))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.primary)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))


                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray)
                        ) {

                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = state.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "ID: ${state.id}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.Person,
                            text = "Edit Profile",
                            iconBackgroundColor = iconBlue
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.Shield,
                            text = "Security",
                            iconBackgroundColor = iconBlue
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.Settings,
                            text = "Setting",
                            iconBackgroundColor = iconBlue
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.Headphones,
                            text = "Help",
                            iconBackgroundColor = iconBlue
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.ExitToApp,
                            text = "Logout",
                            iconBackgroundColor = iconBlue
                        )

                        Spacer(modifier = Modifier.weight(1f))


                    }
                }
            }
        }
    }
}


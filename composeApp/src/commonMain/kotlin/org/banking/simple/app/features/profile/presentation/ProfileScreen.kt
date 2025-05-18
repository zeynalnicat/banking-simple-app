package org.banking.simple.app.features.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.banking.app.features.profile.presentation.components.ProfileMenuItem
import org.banking.simple.app.features.shared.ui.colors.AppColors

@Composable
fun ProfileScreen() {
    val iconBlue = Color(0xFF5B9EFC)

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

                    // Profile Photo
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Nijat Zeynalli",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "ID: 25030024",
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


package org.banking.simple.app.features.dashboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.banking.simple.app.features.shared.ui.colors.AppColors
import org.banking.simple.app.core.Screen


@Composable
fun CardSection(navController: NavController?=null,name:String, lastDigits: String?=null, cardColor: Color = AppColors.blue) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(180.dp)
            .background(cardColor, shape = RoundedCornerShape(20.dp))
            .clickable { navController?.navigate(Screen.Transfer.route) }
    ) {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column{
                Text("VISA", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Platinum", color = Color.White.copy(alpha = 0.8f))
            }

            Column{
                Text(name, color = Color.White)
                Text("\u2022\u2022\u2022\u2022 ${lastDigits}", color = Color.White)
            }
            }

    }
}

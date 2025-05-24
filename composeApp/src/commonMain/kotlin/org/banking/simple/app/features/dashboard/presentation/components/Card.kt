package org.banking.simple.app.features.dashboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.banking.simple.app.features.shared.ui.colors.AppColors
import org.banking.simple.app.core.Screen
import org.banking.simple.app.features.dashboard.domain.models.CardDTO


@Composable
fun CardSection(navController: NavController?=null,cardDTO: CardDTO?,isPreview: Boolean = false) {

    if(isPreview){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(180.dp)
                .background((AppColors.blue),
                  shape = RoundedCornerShape(20.dp))

                .clickable { navController?.navigate(Screen.NewCard.route) }

        ){
            Box(modifier = Modifier.fillMaxSize().graphicsLayer {
                alpha = 0.9f
                shadowElevation = 8.dp.toPx()
                shape = RoundedCornerShape(20.dp)
                clip = true}
                .blur(16.dp)
            )
            Column(modifier = Modifier.fillMaxSize()
                    ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                IconButton(
                   onClick = { {
                       navController?.navigate(Screen.NewCard.route )
                   }}
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCard,
                        contentDescription = "Add Card",
                        tint = Color.Black,
                    )
                }
            }
        }
    }else{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(180.dp)
                .background(Color(cardDTO!!.cardColor.toULong()),
                    shape = RoundedCornerShape(20.dp))
                .clickable { navController?.navigate("transfer/${cardDTO.id}") }
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
                    Text(cardDTO.cardName, color = Color.White)
                    Text("\u2022\u2022\u2022\u2022 ${cardDTO.lastDigits}", color = Color.White)
                }
            }

        }
    }

}

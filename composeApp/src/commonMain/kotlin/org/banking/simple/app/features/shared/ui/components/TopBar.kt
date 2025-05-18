package org.banking.simple.app.features.shared.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.banking.simple.app.features.shared.ui.colors.AppColors


@Composable
fun TopBar(navController: NavController, title: String? = null){
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Back Button", tint = if(title==null) Color.White else Color.Black,
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    navController.popBackStack()
                })

        DSizedBox.sixteenW()
        if(title!=null){
            Text(title, fontSize =20.sp, fontWeight = FontWeight.W600, color = AppColors.primary
            )
        }


    }
}

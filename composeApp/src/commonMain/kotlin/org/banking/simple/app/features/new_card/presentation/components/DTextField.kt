package org.banking.simple.app.features.new_card.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.banking.simple.app.features.shared.ui.components.DSizedBox


@Composable
fun DTextField(value:String, placeHolder:String,onValueChanged: (String)->Unit , label:String ){

    Column {
        Text(label, color = Color.Black)
        DSizedBox.eightH()
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()

        ){
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = {Text(label, color = Color.White)},
                value = value,
                onValueChange = onValueChanged,
                placeholder = { Text(placeHolder, color = Color.Gray) } ,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF23265A),
                    unfocusedContainerColor = Color(0xFF23265A),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedBorderColor = Color(0xFF858BE9),
                    unfocusedBorderColor = Color.Transparent
                )
            )
        }

    }

}
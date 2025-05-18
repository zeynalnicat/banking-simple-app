package org.banking.simple.app.features.shared.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.banking.simple.app.features.new_card.NewCardIntent

@Composable
fun DButton(onClick: (Unit)->Unit,title: String) {
    Button(
        onClick = {onClick},
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2567F9)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
          Text(title)
    }
}
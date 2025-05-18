package org.banking.simple.app.features.shared.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


object DSizedBox {

    @Composable
    fun sixteenH() {
        Spacer(modifier = Modifier.height(16.dp))
    }

    @Composable
    fun sixteenW() {
        Spacer(modifier = Modifier.width(16.dp))
    }

    @Composable

    fun twentyFourH() {
        Spacer(modifier = Modifier.height(24.dp))
    }

    @Composable

    fun eightH() {
        Spacer(modifier = Modifier.height(8.dp))
    }

    @Composable
    fun eightW(){
        Spacer(modifier = Modifier.width(8.dp))
    }
}
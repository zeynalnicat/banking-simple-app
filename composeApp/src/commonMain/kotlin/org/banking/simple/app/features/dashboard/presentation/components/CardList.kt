package org.banking.simple.app.features.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.banking.simple.app.features.dashboard.domain.CardDTO
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity
import org.banking.simple.app.features.shared.ui.colors.AppColors


@Composable
fun HorizontalCardList(
    navController: NavController? = null,
    cards: List<CardDTO>,
//    colors: List<Color> = listOf(AppColors.blue, AppColors.pink, AppColors.yellow)
) {

    if(cards.isEmpty()){
        CardSection(isPreview = true,cardDTO = null, navController = navController)
    }else{
        LazyRow(

            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(cards) { index, card ->

                Box(
                    modifier = Modifier.width(300.dp),
                ){
                    CardSection(
                        navController = navController,
                        cardDTO = card
                    )
                }

            }
        }
    }

}




package org.banking.simple.app.features.transfer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssuredWorkload
import androidx.compose.material.icons.filled.EMobiledata
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.banking.simple.app.features.shared.ui.colors.AppColors
import org.banking.simple.app.features.shared.ui.components.DSizedBox
import org.banking.simple.app.features.shared.ui.components.TopBar


@Composable
fun CardDetailsScreen(navController: NavController) {
    val operationItems = listOf(
        OperationItem(Icons.Default.Wallet, "Top Up"),
        OperationItem(Icons.Default.Send, "Send"),
    )

    val paymentLists = listOf(
        OperationItem(Icons.Default.Lightbulb,"Electricity"),
        OperationItem(Icons.Default.AssuredWorkload,"Assurance"),
        OperationItem(Icons.Default.EMobiledata,"Mobile Credit"),
        OperationItem(Icons.Default.ShoppingCart,"Merchant"),
        OperationItem(Icons.Default.Fastfood,"Food"),
        OperationItem(Icons.Default.Healing,"Health")
    )
    Scaffold(
        topBar = {
            TopBar(navController)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .background(AppColors.primary)
                    .align(Alignment.TopStart)
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Available Balance:", color = Color.Gray)
                    DSizedBox.eightH()
                    Text("$500", fontSize = 40.sp, color = Color.White)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 200.dp, start = 8.dp, end=8.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        operationItems.forEach { item ->
                            Column(
                                modifier = Modifier.padding(vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name,
                                    modifier = Modifier.size(32.dp),
                                    tint = AppColors.primary
                                )
                                DSizedBox.eightH()
                                Text(text = item.name)
                            }
                        }
                    }
                }

                DSizedBox.twentyFourH()
                Spacer(modifier = Modifier.width(16.dp))
                Text("Payment List", fontSize = 18.sp, fontWeight = FontWeight.W600)
                DSizedBox.sixteenH()
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(paymentLists.size) { index ->
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.fillMaxWidth()
                                    .clickable {  }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Icon(
                                        imageVector = paymentLists[index].icon,
                                        contentDescription =paymentLists[index].name,
                                        modifier = Modifier.size(24.dp),
                                        tint = AppColors.primary
                                    )
                                    DSizedBox.sixteenW()
                                    Text(text = paymentLists[index].name)
                                }
                            }
                        }
                    }
                }


            }
        }

    }

    }



data class OperationItem (
    var icon : ImageVector,
    var name: String,
)
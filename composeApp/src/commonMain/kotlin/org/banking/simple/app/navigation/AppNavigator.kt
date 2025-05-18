package org.banking.simple.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.banking.simple.app.core.DaoHolder
import org.banking.simple.app.core.Screen
import org.banking.simple.app.features.auth.presentation.AuthScreen
import org.banking.simple.app.features.dashboard.presentation.DashboardScreen
import org.banking.simple.app.features.new_card.presentation.NewCardScreen
import org.banking.simple.app.features.profile.presentation.ProfileScreen
import org.banking.simple.app.features.transfer.presentation.TransferScreen

@Composable
fun AppNavigator(innerPadding: PaddingValues, navController: NavHostController,daoHolder: DaoHolder) {

    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screen.Dashboard.route) { DashboardScreen(navController) }
        composable ( Screen.Transfer.route)  { TransferScreen(navController) }
        composable (Screen.NewCard.route) { NewCardScreen(navController,daoHolder.cardDao) }
        composable (Screen.Profile.route ) {ProfileScreen()}
        composable (Screen.Auth.route) { AuthScreen(daoHolder.userDao,navController) }
    }
}

package org.banking.simple.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.banking.simple.app.core.DaoHolder
import org.banking.simple.app.core.Screen
import org.banking.simple.app.features.dashboard.data.local.CardDao
import org.banking.simple.app.navigation.AppNavigator
import org.banking.simple.app.navigation.TopLevelRoute

@Composable
@Preview
fun App(daoHolder: DaoHolder) {
    val navController = rememberNavController()

    val topLevelRoutes = listOf(
        TopLevelRoute(Screen.Dashboard.route, "Dashboard", Icons.Default.Home),
        TopLevelRoute(Screen.Profile.route, "Profile", Icons.Default.AccountBox)
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val isUserLoggedIn = remember { mutableStateOf(false) }

    MaterialTheme {
        Scaffold(
            bottomBar = {
                if (isUserLoggedIn.value && currentRoute !in listOf(Screen.Auth.route)) {
                    NavigationBar {
                        topLevelRoutes.forEach { route ->
                            val selected = currentRoute == route.route
                            NavigationBarItem(
                                icon = { Icon(route.icon, contentDescription = route.name) },
                                label = { Text(route.name) },
                                selected = selected,
                                onClick = {
                                    if (!selected) {
                                        navController.navigate(route.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            AppNavigator(innerPadding, navController, daoHolder)
        }
    }
}

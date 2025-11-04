package net.iessochoa.sergiocontreras.demomvvmt07_1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.MainScreenDestination

/**
 * Project: DemoMVVMT07-1
 * From: net.iessochoa.sergiocontreras.demomvvmt07_1.ui.navigation
 * Created by: Contr
 * On: 04/11/2025 at 11:00
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Composable
fun MonsterClickerNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenDestination.route,
        modifier = modifier
    ) {

    }



}
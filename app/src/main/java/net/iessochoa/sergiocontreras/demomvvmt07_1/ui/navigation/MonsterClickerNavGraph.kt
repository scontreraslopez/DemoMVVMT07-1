package net.iessochoa.sergiocontreras.demomvvmt07_1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.InventoryScreen
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.InventoryScreenDestination
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.MainScreen
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.MainScreenDestination
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.MonsterViewModel

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

    val monsterViewModel: MonsterViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = MainScreenDestination.route,
        modifier = modifier
    ) {
        composable(route = MainScreenDestination.route) {
            MainScreen(
                monsterViewModel = monsterViewModel,
                modifier = modifier,
                onNavigateToInventory = {
                    navController.navigate(InventoryScreenDestination.route)
                }
            )
        }
        composable(route = InventoryScreenDestination.route) {
            InventoryScreen(
                monsterViewModel = monsterViewModel,
                modifier = modifier
            )

        }


    }



}
package net.iessochoa.sergiocontreras.demomvvmt07_1

import androidx.compose.runtime.Composable

/**
 * Project: DemoMVVMT07-1
 * From: net.iessochoa.sergiocontreras.demomvvmt07_1
 * Created by: Contr
 * On: 04/11/2025 at 10:49
 * Creado en Settings -> Editor -> File and Code Templates
 */


/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun MonsterClickerApp(navController: NavHostController = rememberNavController()) {
    InventoryNavHost(navController = navController)
}
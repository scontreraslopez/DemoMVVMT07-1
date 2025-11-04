package net.iessochoa.sergiocontreras.demomvvmt07_1.ui.navigation

/**
 * Project: DemoMVVMT07-1
 * From: net.iessochoa.sergiocontreras.demomvvmt07_1.ui.navigation
 * Created by: Contr
 * On: 04/11/2025 at 10:47
 * Creado en Settings -> Editor -> File and Code Templates
 */

// Lo primero es la estructura de navigation y a partir de ahí la interface de NavigationDestination

interface NavigationDestination {
    /**
     * Unique name to define the path for a composable
     */
    val route: String

    /**
     * String resource id to that contains title to be displayed for the screen.
     */
    val titleRes: Int
}
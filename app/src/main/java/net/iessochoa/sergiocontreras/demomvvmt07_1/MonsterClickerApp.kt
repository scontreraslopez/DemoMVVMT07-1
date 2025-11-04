package net.iessochoa.sergiocontreras.demomvvmt07_1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.navigation.MonsterClickerNavHost

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
fun MonsterClickerApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    MonsterClickerNavHost(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonsterClickerTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


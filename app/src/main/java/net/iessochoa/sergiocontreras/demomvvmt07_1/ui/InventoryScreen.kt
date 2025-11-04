package net.iessochoa.sergiocontreras.demomvvmt07_1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.demomvvmt07_1.R
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.navigation.NavigationDestination
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.theme.Typography

/**
 * Project: DemoMVVMT07-1
 * From: net.iessochoa.sergiocontreras.demomvvmt07_1.ui
 * Created by: Contr
 * On: 04/11/2025 at 10:26
 * Creado en Settings -> Editor -> File and Code Templates
 */

/** Esta es para trabajar la navegación
 * la idea es que en MainScreen tengas un botón con un cofre
 * al hacerle click navegas a tu inventario
 * tu inventario de momento solo mostrará gold: $bodycount
 * abajo una imagen de monedas de oro, si y solo si gold > 0
 */

object InventoryScreenDestination: NavigationDestination {
    override val route = "inventory"
    override val titleRes = R.string.screen_inventory
}

@Composable
fun InventoryScreen(
    monsterViewModel: MonsterViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by monsterViewModel.uiState.collectAsState() // así esta casi listo
    val bodyCount = uiState.bodyCount

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "gold: $bodyCount",
            style = Typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        if (bodyCount > 0) {
            Image(
                painter = painterResource(R.drawable.gold),
                contentDescription = "Gold"
            )
        }
    }
}

/*
TODO FIX preview
@Preview(showBackground = true)
@Composable
fun InventoryScreenPreviewWithGold() {
    InventoryScreen(bodyCount = 100)
}

@Preview(showBackground = true)
@Composable
fun InventoryScreenPreviewWithoutGold() {
    InventoryScreen(bodyCount = 0)
}
*/
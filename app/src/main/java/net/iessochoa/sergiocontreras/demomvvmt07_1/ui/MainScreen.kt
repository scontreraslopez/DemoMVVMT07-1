package net.iessochoa.sergiocontreras.demomvvmt07_1.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.demomvvmt07_1.R
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.components.InventoryButton
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.navigation.NavigationDestination

object MainScreenDestination: NavigationDestination {
    override val route = "main"
    override val titleRes = R.string.app_name
}
@Composable
fun MainScreen(
    monsterViewModel: MonsterViewModel = viewModel(),  //Recuerda importar lifecycle dependencies en gradle o no tirará
    onNavigateToInventory: () -> Unit, //Lambda navegacion
    modifier: Modifier = Modifier
){


    // 1. EL ESTADO: Ahora fetén con el viewModel
    val uiState by monsterViewModel.uiState.collectAsState() // así esta casi listo
    val bodyCount = uiState.bodyCount // así tengo que toquitear menos
    val onAttack = { monsterViewModel.onAttack() } // Pasamos la lógica como una lambda

    // 2. LA UI: Pasamos el estado y la lógica a los componentes "tontos"
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Pasamos los datos del estado uno por uno
        MonsterCard(
            name = uiState.name,
            currentHp = uiState.currentHp,
            maxHp = uiState.maxHp,
            spriteId = uiState.spriteId,
            onAttack =  onAttack // Pasamos el evento (la lógica)
        )

        StatsPanel(
            bodyCount = bodyCount // Pasamos el contador
        )

        InventoryButton(onClick = onNavigateToInventory)

    }
}

/* * Componente de UI (Vista) "Tonto"
 * Solo recibe datos y notifica un clic (lambda).
 */
@Composable
fun MonsterCard(
    name: String,
    currentHp: Int,
    maxHp: Int,
    @DrawableRes spriteId: Int,
    onAttack: () -> Unit, // Recibe la lógica como una lambda
    modifier: Modifier = Modifier.Companion
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            // Hacemos que la tarjeta sea clicable
            .clickable(onClick = onAttack, enabled = currentHp > 0)
    ) {
        Column(
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            modifier = Modifier.Companion
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Companion.Bold
            )
            Image(
                painter = painterResource(id = spriteId),
                contentDescription = name,
                modifier = Modifier.Companion
                    .size(150.dp)
                    .padding(8.dp)
                    .border(2.dp, Color.Companion.Gray)
            )
            HpBar(currentHp = currentHp, maxHp = maxHp)

        }
    }
}

/* * Componente de UI (Vista) "Tonto"
 * Solo muestra la barra de vida.
 */
@Composable
fun HpBar(currentHp: Int, maxHp: Int, modifier: Modifier = Modifier.Companion) {
    // Calculamos el progreso (de 0.0f a 1.0f)
    val progress = (currentHp.toFloat() / maxHp.toFloat()).coerceIn(0f, 1f)

    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier
            .fillMaxWidth()
            .height(16.dp)
            .padding(vertical = 4.dp)
    )
    Text(
        text = "$currentHp / $maxHp",
        fontSize = 20.sp,
        fontWeight = FontWeight.Companion.SemiBold
    )
}

/* * Componente de UI (Vista) "Tonto"
 * Solo muestra las estadísticas.
 */
@Composable
fun StatsPanel(bodyCount: Int, modifier: Modifier = Modifier.Companion) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Damage: 1-3", fontSize = 16.sp)
        Text(text = "Body Count: $bodyCount", fontSize = 16.sp)
    }
}


/*
TODO fix preview
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(

    )
}
*/
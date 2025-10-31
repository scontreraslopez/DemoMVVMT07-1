package net.iessochoa.sergiocontreras.demomvvmt07_1


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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 * 4. Creamos un Estado "fullero" para la UI.
 * Lo hacemos con rememberSaveable sepa guardarlo
 * al rotar la pantalla.
 */

data class MonsterUiClass(
    val name: String,
    val maxHp: Int,
    val currentHp: Int,
    @DrawableRes val spriteId: Int
)



/**
 * Función helper para crear el estado inicial la primera vez.
 * Llama al Repositorio (una "fuente de datos").
 */
private fun createInitialState(): MonsterUiClass {
    // Pedimos el primer monstruo al repositorio
    val monster = MonsterRepository.getNextMonster()
    return MonsterUiClass(
        name = monster.name,
        maxHp = monster.maxHp,
        currentHp = monster.maxHp,
        spriteId = monster.spriteId)
}

/*
 * 5. LA VISTA "SABELOTODO" (El Composable "Fullero")
 * Esta vista CONTIENE el estado y la LÓGICA DE NEGOCIO.
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {


    // 1. EL ESTADO: Creamos el estado y lo recordamos (¡y lo guardamos al rotar!)
    var uiState by rememberSaveable {
        mutableStateOf(createInitialState())
    }

    var bodyCount by rememberSaveable { mutableStateOf(0) } // Int Lo iniciamos a 0.

    // 2. LA LÓGICA DE NEGOCIO: ¡La definimos DENTRO de la Vista! (MAL)
    fun onAttack() {
        // Lógica de daño
        val damage = (1..3).random()
        val newHp = (uiState.currentHp - damage).coerceAtLeast(0) //coerceAtLeast es para forzar 0+

        if (newHp == 0) {
            // Lógica de "monstruo derrotado"
            val newMonster = MonsterRepository.getNextMonster()
            uiState = uiState.copy(
                name = newMonster.name,
                maxHp = newMonster.maxHp,
                currentHp = newMonster.maxHp,
                spriteId = newMonster.spriteId,
            )
            bodyCount++ // Actualizamos el contador
        } else {
            // Lógica de "sigue vivo"
            uiState = uiState.copy(currentHp = newHp)
        }
    }

    // 3. LA UI: Pasamos el estado y la lógica a los componentes "tontos"
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Pasamos los datos del estado uno por uno
        MonsterCard(
            name = uiState.name,
            currentHp = uiState.currentHp,
            maxHp = uiState.maxHp,
            spriteId = uiState.spriteId,
            onAttack = { onAttack() } // Pasamos el evento (la lógica)
        )

        StatsPanel(
            bodyCount = bodyCount // Pasamos el contador
        )
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
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            // Hacemos que la tarjeta sea clicable
            .clickable(onClick = onAttack, enabled = currentHp > 0)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = spriteId),
                contentDescription = name,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .border(2.dp, Color.Gray)
            )
            HpBar(currentHp = currentHp, maxHp = maxHp)
            Text(
                text = "$currentHp / $maxHp",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

/* * Componente de UI (Vista) "Tonto"
 * Solo muestra la barra de vida.
 */
@Composable
fun HpBar(currentHp: Int, maxHp: Int, modifier: Modifier = Modifier) {
    // Calculamos el progreso (de 0.0f a 1.0f)
    val progress = (currentHp.toFloat() / maxHp.toFloat()).coerceIn(0f, 1f)

    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier
            .fillMaxWidth()
            .height(16.dp)
            .padding(vertical = 4.dp)
    )
}

/* * Componente de UI (Vista) "Tonto"
 * Solo muestra las estadísticas.
 */
@Composable
fun StatsPanel(bodyCount: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Damage: 1-3", fontSize = 16.sp)
        Text(text = "Body Count: $bodyCount", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
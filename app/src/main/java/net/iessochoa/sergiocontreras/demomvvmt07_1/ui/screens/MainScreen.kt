package net.iessochoa.sergiocontreras.demomvvmt07_1.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.demomvvmt07_1.data.MonsterRepository
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.MainScreenUiState
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.components.MonsterCard
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.components.StatsPanel
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.theme.DemoMVVMT071Theme


/*
    Paso 3: Quito de la vista el estado.
    Modifico la firma de la pantalla para recibir el estado.

    Puedo pasar el uiState como un conjunto, o podría separarlo más.
    A priori es más o menos lo mismo mientras lo gestionemos bien.
    En el codelab le enchufa a la vista el viewModel entero, eso tiene un problema
    y es que fastidia el preview... a mí no me gusta.

    El viewModel lo cogemos en la clase App() o MainActivity() donde tengamos nuestro
    entry point a la aplicación.

    La parte de la lógica la he comentado por que la voy a hacer en un paso 4 para que quede
    un poco más claro.

    Cuestiones que he tenido que ñapear:
        - el onAttack lo he comentado entero, ya lo migraré a viewModel. O sea
        temporalmente he "roto" la aplicación ya no hace nada
        - el currentHp se me había olvidado meterlo en mi uiState. Así que lo he fijado
        a 10 y lo he dejado con un TODO

    En este punto ya puedo probar la aplicación y no ataca pero se tiene que ver
 */

/*
 * 5. LA VISTA OMNISCIENTE y OMNIPOTENTE (El Composable "Fullero")
 * Esta vista CONTIENE el estado y la LÓGICA DE NEGOCIO.
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    uiState: MainScreenUiState
) {

    val currentMonster = uiState.currentMonster
    val bodyCount = uiState.bodyCount

// 2. LA LÓGICA DE NEGOCIO: ¡La definimos DENTRO de la Vista! (MAL)
    fun onAttack() {
        /*
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
        */

    }

    // 3. LA UI: Pasamos el estado y la lógica a los componentes "tontos"
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Pasamos los datos del estado uno por uno
        MonsterCard(
            name = currentMonster.name,
            currentHp = 10, //TODO currentMonster.currentHp,
            maxHp = currentMonster.maxHp,
            spriteId = currentMonster.spriteId,
            onAttack = { onAttack() } // Pasamos el evento (la lógica)
        )

        StatsPanel(
            bodyCount = bodyCount // Pasamos el contador
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    /* Genero un estado inicial para el preview */
    val uiState = MainScreenUiState(
        currentMonster = MonsterRepository.getNextMonster(),
        bodyCount = 0
    )

    DemoMVVMT071Theme {
        MainScreen(
            uiState = uiState
        )
    }
}
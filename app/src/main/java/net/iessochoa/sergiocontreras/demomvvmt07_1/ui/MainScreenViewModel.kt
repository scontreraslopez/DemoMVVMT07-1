package net.iessochoa.sergiocontreras.demomvvmt07_1.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.sergiocontreras.demomvvmt07_1.data.MonsterRepository

/*

    Paso 2: Creamos el ViewModel, la primera parte es siempre igual:

    2.1 contenedor privado _uiState que es un MutableStateFlow del uiState ¡con su estado inicial!
    2.2. uiState que es un StateFlow<> (tipado a nuestro uiState), y se instancia con _uiState.asStateFlow()

    ¿De dónde recupero su estado inicial? Pues si no le he dado valores por defecto a UiState me lo debería traer de la fuente de datos
    En este caso el repositorio, lo BUENO sería hacer inyección de dependencias, pero queda un poco fuera del alcance de momento
    así que acoplamos el repositorio dentro del cuerpo del ViewModel para salir del paso.


    Parte del paso 4: Como nos ha tocado modificar la clase uiState
    pues nos toca apañar también el estado inicial del viewModel

    Paso 5: Movemos la lógica (o la implementamos).
    El patron es el mismo siempre, el _uiState (el privado) con un .update { }
    calculamos el nuevo estado y lo planchamos sobreescribiendo currenteState con el .copy()

 */

class MainScreenViewModel: ViewModel() {

    private val repository = MonsterRepository
    private val initialMonster = repository.getNextMonster()

    private val _uiState = MutableStateFlow(
        MainScreenUiState(
            currentMonster = initialMonster,
            currentMonsterHP = initialMonster.maxHp, // Lo hacemos
            bodyCount = 0
        )
    )
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    // Lógica de daño

    fun onAttack() {

        val damage = (1..3).random()

        _uiState.update { currentState ->

            val newHp =
                (currentState.currentMonsterHP - damage).coerceAtLeast(0) //coerceAtLeast es para forzar 0+

            if (newHp == 0) {
                // Lógica de "monstruo derrotado"
                val newMonster = MonsterRepository.getNextMonster()

                currentState.copy(
                    currentMonster = newMonster,
                    currentMonsterHP = newMonster.maxHp,
                    bodyCount = currentState.bodyCount + 1 // Actualizamos el contador
                )
            } else {
                currentState.copy(
                    currentMonsterHP = newHp
                )
            }
        }
    }


}
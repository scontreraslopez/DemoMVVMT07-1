package net.iessochoa.sergiocontreras.demomvvmt07_1.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iessochoa.sergiocontreras.demomvvmt07_1.data.MonsterRepository

/*

    Paso 2: Creamos el ViewModel, la primera parte es siempre igual:

    2.1 contenedor privado _uiState que es un MutableStateFlow del uiState ¡con su estado inicial!
    2.2. uiState que es un StateFlow<> (tipado a nuestro uiState), y se instancia con _uiState.asStateFlow()

    ¿De dónde recupero su estado inicial? Pues si no le he dado valores por defecto a UiState me lo debería traer de la fuente de datos
    En este caso el repositorio, lo BUENO sería hacer inyección de dependencias, pero queda un poco fuera del alcance de momento
    así que acoplamos el repositorio dentro del cuerpo del ViewModel para salir del paso.



 */

class MainScreenViewModel: ViewModel() {

    private val repository = MonsterRepository

    private val _uiState = MutableStateFlow(
        MainScreenUiState(
            currentMonster = repository.getNextMonster(),
            bodyCount = 0
        )
    )
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()


}
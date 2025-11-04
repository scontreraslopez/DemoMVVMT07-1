package net.iessochoa.sergiocontreras.demomvvmt07_1.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.sergiocontreras.demomvvmt07_1.data.MonsterRepository

/**
 * Project: DemoMVVMT07-1
 * From: net.iessochoa.sergiocontreras.demomvvmt07_1.ui
 * Created by: Contr
 * On: 04/11/2025 at 09:46
 * Creado en Settings -> Editor -> File and Code Templates
 */
class MonsterViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MonsterUiState())
    val uiState: StateFlow<MonsterUiState> = _uiState.asStateFlow()

    fun onAttack(minDamage: Int = 1, maxDamage: Int = 3) {
        //1. Obtener el estado actual
        val currentState = _uiState.value

        // Lógica de daño
        val damage = (minDamage..maxDamage).random()
        val newHp = (currentState.currentHp - damage).coerceAtLeast(0) //coerceAtLeast es para forzar 0+


        if (newHp == 0) {
            // Lógica de "monstruo derrotado"
            // Pillo un nuevo monstruo y bodycount++
            val newMonster = MonsterRepository.getNextMonster()
            _uiState.update {
                it.copy(
                    name = newMonster.name,
                    currentHp = newMonster.maxHp,
                    maxHp = newMonster.maxHp,
                    spriteId = newMonster.spriteId,
                    bodyCount = currentState.bodyCount + 1
                )
            }
        } else {
            // Lógica de "sigue vivo", solo tengo que actualizar el hp
            _uiState.update {
                it.copy(
                    currentHp = newHp
                    )
                }
            }
        }
}



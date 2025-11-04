package net.iessochoa.sergiocontreras.demomvvmt07_1.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import net.iessochoa.sergiocontreras.demomvvmt07_1.data.MonsterRepository

/**
 * Project: DemoMVVMT07-1
 * From: net.iessochoa.sergiocontreras.demomvvmt07_1.ui
 * Created by: Contr
 * On: 04/11/2025 at 09:38
 * Creado en Settings -> Editor -> File and Code Templates
 */

/** 1. Creo esta clase de estado. Pienso en los datos que necesito para dibujar la UI
        name = uiState.name,
        currentHp = uiState.currentHp,
        maxHp = uiState.maxHp,
        spriteId = uiState.spriteId,
*/

data class MainScreenUiState (
    val name: String = MonsterRepository.getNextMonster().name,
    val currentHp: Int = MonsterRepository.getNextMonster().maxHp,  //Al principio current = max
    val maxHp: Int = MonsterRepository.getNextMonster().maxHp,
    @DrawableRes val spriteId: Int = MonsterRepository.getNextMonster().spriteId
)
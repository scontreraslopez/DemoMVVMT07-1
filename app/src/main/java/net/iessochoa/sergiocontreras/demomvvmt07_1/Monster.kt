package net.iessochoa.sergiocontreras.demomvvmt07_1

import androidx.annotation.DrawableRes

public data class Monster (
    val name: String,
    val maxHp: Int,
    @DrawableRes val spriteId: Int
) {
}
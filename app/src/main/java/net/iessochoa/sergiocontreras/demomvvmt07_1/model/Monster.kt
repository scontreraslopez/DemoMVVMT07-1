package net.iessochoa.sergiocontreras.demomvvmt07_1.model

import androidx.annotation.DrawableRes

data class Monster (
    val name: String,
    val maxHp: Int,
    @DrawableRes val spriteId: Int
)
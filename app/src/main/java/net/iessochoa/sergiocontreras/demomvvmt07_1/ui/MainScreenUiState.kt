package net.iessochoa.sergiocontreras.demomvvmt07_1.ui

import net.iessochoa.sergiocontreras.demomvvmt07_1.model.Monster

/*
Paso 1: Lo primero es crear una clase que servirá del almacén para el estado de la pantalla.
Entonces debemos analizar la aplicación y reflexionar: ¿qué datos necesito para pintar la pantalla en
un momento dado?

En este momento sólo hace falta dos:
- El monstruo contra el que estoy luchando
- El contador de monstruos derrotados (que he llamado body Count)

A tal efecto creamos una data class que va a servir de almacén del estado.

Es posible darle un valor por defecto.
*/

/*
   Paso 4: Este paso no sucede siempre pero a veces pasa que se te olvida algo y tienes que volver,
   En este caso hemos detectado que en el estado no vale solo con el monstruo, sino que hace falta también
   guardar su salud.
 */

data class MainScreenUiState(
    val currentMonster: Monster,
    val currentMonsterHP: Int,
    val bodyCount: Int
)

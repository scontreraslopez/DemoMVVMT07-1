package net.iessochoa.sergiocontreras.demomvvmt07_1

// Esta clase simula una fuente de datos (como una BBDD o una API)
// Es un stub/driver para proveer datos falsos (fake data).
object MonsterRepository {

    // Lista privada de monstruos (nuestra "base de datos" falsa)
    private val monsters = listOf(
        Monster(
            name = "Goblin Archer",
            maxHp = 10,
            // Asegúrate de tener 'goblin_archer' en res/drawable
            spriteId = R.drawable.goblin_archer
        ),
        Monster(
            name = "Gigant Spider",
            maxHp = 15,
            // Asegúrate de tener 'gigant_spider' en res/drawable
            spriteId = R.drawable.gigant_spider
        )
    )

    /**
     * Devuelve un monstruo aleatorio de nuestra lista.
     */
    fun getNextMonster(): Monster {
        return monsters.random()
    }
}
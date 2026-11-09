package net.iessochoa.sergiocontreras.demomvvmt07_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.theme.DemoMVVMT071Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoMVVMT071Theme {
                MonsterFighterApp()
            }
        }
    }
}

/* 1. Despejamos la clase MainActivity
   2. Creamos la data clase del modelo Monster
   3. Creamos el repository de monsters, que nos dará datos fake. Importamos sus assets también
   4. Creamos la view, nuestros @Composables de toda la vida. Intentamos usar componentes separados.
   5. La solución de ahora tiene una serie de limitaciones:
     - Testeabilidad
     - El monstruo se resetea al rotar
     - No sigue las mejores prácticas de separación de responsabilidades
*/
package net.iessochoa.sergiocontreras.demomvvmt07_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.theme.DemoMVVMT071Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoMVVMT071Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/* 1. Despejamos la clase MainActivity
   2. Creamos la data clase del modelo Monster
   3. Creamos el repository de monsters, que nos dará datos fake. Importamos sus assets también
   4. Creamos la view, nuestros @Composables de toda la vida. Intentamos usar componentes separados.

*/
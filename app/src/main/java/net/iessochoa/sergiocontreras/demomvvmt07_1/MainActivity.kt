package net.iessochoa.sergiocontreras.demomvvmt07_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.theme.DemoMVVMT071Theme
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoMVVMT071Theme {
                    MonsterClickerApp()
            }
        }
    }
}

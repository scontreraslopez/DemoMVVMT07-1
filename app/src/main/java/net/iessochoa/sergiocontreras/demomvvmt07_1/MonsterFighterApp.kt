package net.iessochoa.sergiocontreras.demomvvmt07_1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.screens.MainScreen

@Composable
fun MonsterFighterApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MainScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}
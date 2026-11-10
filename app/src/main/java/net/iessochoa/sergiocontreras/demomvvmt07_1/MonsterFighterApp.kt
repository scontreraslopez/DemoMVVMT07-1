package net.iessochoa.sergiocontreras.demomvvmt07_1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.MainScreenViewModel
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.screens.MainScreen

@Composable
fun MonsterFighterApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        // Instancio el viewModel y me suscribo a su estado. Parte del paso 2.
        val viewModel: MainScreenViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        MainScreen(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState
        )
    }
}
package net.iessochoa.sergiocontreras.demomvvmt07_1.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.theme.DemoMVVMT071Theme


/* * Componente de UI (Vista) "Tonto"
 * Solo muestra las estadísticas.
 */
@Composable
fun StatsPanel(bodyCount: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Damage: 1-3", fontSize = 16.sp)
        Text(text = "Body Count: $bodyCount", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun StatsPanelPreview() {
    DemoMVVMT071Theme {
        StatsPanel(bodyCount = 5)
    }
}
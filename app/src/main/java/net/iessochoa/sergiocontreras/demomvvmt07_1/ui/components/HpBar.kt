package net.iessochoa.sergiocontreras.demomvvmt07_1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.theme.DemoMVVMT071Theme


/* * Componente de UI (Vista) "Tonto"
 * Solo muestra la barra de vida.
 */
@Composable
fun HpBar(currentHp: Int, maxHp: Int, modifier: Modifier = Modifier) {
    // Calculamos el progreso (de 0.0f a 1.0f)
    val progress = (currentHp.toFloat() / maxHp.toFloat()).coerceIn(0f, 1f)
    Column(
        modifier =  modifier.fillMaxWidth()
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .padding(vertical = 4.dp)
        )
        Text(
            text = "$currentHp / $maxHp",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HpBarPreview() {
    DemoMVVMT071Theme {
        HpBar(
            currentHp = 10,
            maxHp = 20
        )
    }
}

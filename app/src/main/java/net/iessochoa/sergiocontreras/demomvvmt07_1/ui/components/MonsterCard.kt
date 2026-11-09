package net.iessochoa.sergiocontreras.demomvvmt07_1.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.sergiocontreras.demomvvmt07_1.data.MonsterRepository
import net.iessochoa.sergiocontreras.demomvvmt07_1.ui.theme.DemoMVVMT071Theme


/* * Componente de UI (Vista) "Tonto"
 * Solo recibe datos y notifica un clic (lambda).
 */
@Composable
fun MonsterCard(
    name: String,
    currentHp: Int,
    maxHp: Int,
    @DrawableRes spriteId: Int,
    onAttack: () -> Unit, // Recibe la lógica como una lambda
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            // Hacemos que la tarjeta sea clicable
            .clickable(onClick = onAttack, enabled = currentHp > 0)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = spriteId),
                contentDescription = name,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .border(2.dp, Color.Gray)
            )
            HpBar(currentHp = currentHp, maxHp = maxHp)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MonsterPreview() {
    val monster = MonsterRepository.getNextMonster()
    DemoMVVMT071Theme {
        MonsterCard(
            name = monster.name,
            currentHp = monster.maxHp / 2,
            maxHp = monster.maxHp,
            spriteId = monster.spriteId,
            onAttack = { }
        )
    }
}

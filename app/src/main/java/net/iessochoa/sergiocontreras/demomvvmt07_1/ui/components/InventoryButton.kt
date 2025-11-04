package net.iessochoa.sergiocontreras.demomvvmt07_1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.demomvvmt07_1.R

/**
 * Project: DemoMVVMT07-1
 * From: net.iessochoa.sergiocontreras.demomvvmt07_1.ui.components
 * Created by: Contr
 * On: 04/11/2025 at 11:12
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Composable
fun InventoryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .size(80.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .clickable { onClick() } //
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.chest),
            contentDescription = "Inventory",
            modifier = Modifier.weight(0.75f)

        )
        Text(
            text = "Inventory",
            modifier = Modifier.weight(0.25f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryButtonPreview() {
    InventoryButton {}
}

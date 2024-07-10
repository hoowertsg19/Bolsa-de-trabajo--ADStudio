package com.uam.bolsatrabajo.compose.inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.ui.theme.SemiTransparentBlue

@Composable
fun PantallaInicio(
    onParticipanteClick: () -> Unit,
    onEmpresaClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_principal),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Image(
            painter = painterResource(id = R.drawable.logo_30_aniversario),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .padding(horizontal = 32.dp),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = { onParticipanteClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Participante UAM", color = Color.White, fontFamily = montserrat, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onEmpresaClick() },
            colors = ButtonDefaults.buttonColors(containerColor = SemiTransparentBlue),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Empresa", color = Color(0xFF0099A8), fontFamily = montserrat, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaInicioPreview() {
    BolsaTrabajoTheme {
        PantallaInicio(
            onParticipanteClick = {},
            onEmpresaClick = {}
        )
    }
}

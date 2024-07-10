package com.uam.bolsatrabajo.compose.registroUsuario

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme

@Composable
fun RegistroExitoso(onContinueClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = "¡Registro exitoso!",
            fontSize = 24.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0099A8),
        )
        Spacer(modifier = Modifier.height(80.dp))
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

        Text(
            text = "Click a Continuar para iniciar sesión.",
            fontSize = 12.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )


        Button(
            onClick = { onContinueClick() },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Continuar", color = Color.White, fontFamily = montserrat, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroExitosoPreview() {
    BolsaTrabajoTheme {
        RegistroExitoso(onContinueClick = {})
    }
}

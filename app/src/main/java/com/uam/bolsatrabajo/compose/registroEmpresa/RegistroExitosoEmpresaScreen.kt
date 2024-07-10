package com.uam.bolsatrabajo.compose.registroEmpresa

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uam.bolsatrabajo.R
import androidx.navigation.NavHostController
import com.uam.bolsatrabajo.bienvenida.montserrat

@Composable
fun RegistroExitosoEmpresaScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Registro está casi completo!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            color = Color(0xFF0099A8),
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Tu cuenta de empresa estará activa una vez que el administrador apruebe tu solicitud.",
            fontSize = 20.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(80.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_30_aniversario),
            contentDescription = null,
            modifier = Modifier
                .height(250.dp)
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Por favor, revisar el correo electrónico para cualquier actualización.",
            fontSize = 12.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { navController.navigate("pantalla_inicio") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Inicio",
                color = Color.White,
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}

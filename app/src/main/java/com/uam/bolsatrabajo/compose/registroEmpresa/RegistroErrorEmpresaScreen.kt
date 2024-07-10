package com.uam.bolsatrabajo.compose.registroEmpresa

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat

@Composable
fun RegistroErrorEmpresaScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.logo_principal),
                contentDescription = null,
                modifier = Modifier
                    .height(125.dp)
                    .align(Alignment.TopStart)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "¡Error en el Registro!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            color = Color.Red,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Hubo un problema al intentar registrar tu empresa. Por favor, intenta nuevamente.",
            fontSize = 20.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

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
            text = "Por favor, intenta nuevamente más tarde.",
            fontSize = 12.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { navController.navigate("soli_empresa") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Reintentar",
                color = Color.White,
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    heightDp = 820
)
@Composable
fun PreviewRegistroErrorEmpresaScreen() {
    val navController = rememberNavController()
    RegistroErrorEmpresaScreen(navController)
}

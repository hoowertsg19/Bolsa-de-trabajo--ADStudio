package com.uam.bolsatrabajo.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.relay.compose.RelayText
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat

@SuppressLint("InvalidColorHexValue")
@Composable
fun TerminosScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF0099A8),Color.Black.copy(1f),Color.Black,Color.Black.copy(1f),Color(0xFF0099A8)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(horizontal = 30.dp, vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.uamletras),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    RelayText(
                        content = "Términos y Condiciones",
                        fontSize = 24.sp,
                        fontFamily = montserrat,
                        color = Color.White,
                        height = 1.2.em,
                        fontWeight = FontWeight.Bold,
                        maxLines = -1,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = """
                            Al registrarse, la empresa acepta:
                            
                            • Proporcionar información veraz.
                            
                            • Usar la plataforma solo para contratación.
                            
                            • Manejar confidencialmente los datos de candidatos.
                            
                            • Actualizar y eliminar vacantes no disponibles.
                            
                            • Reconocer los derechos de la UAM sobre la plataforma.
                            
                            • Posibles modificaciones a estos términos.
                            
                            • Eximir a la UAM de responsabilidades por el uso de la plataforma.
                        """.trimIndent(),
                        fontFamily = montserrat,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp),
                        textAlign = TextAlign.Left
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("next_screen") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RelayText(
                            content = "Aceptar y Continuar",
                            fontSize = 12.sp,
                            fontFamily = montserrat,
                            color = Color.White,
                            height = 1.2.em,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = -1,
                            shadow = Shadow(
                                color = Color(
                                    alpha = 127,
                                    red = 0,
                                    green = 0,
                                    blue = 0
                                ),
                                offset = Offset(
                                    x = 0.0f,
                                    y = 5.12f
                                ),
                                blurRadius = 5.12f
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Al hacer clic en \"Aceptar y Continuar\", confirma haber leído, entendido y aceptado estos términos.",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    widthDp = 393, // Width in dp (approximately the width of 1080 pixels at 2.75 dp per pixel)
    heightDp = 820  // Height in dp (approximately the height of 2400 pixels at 2.75 dp per pixel)
)
@Composable
fun PreviewTerminosScreen() {
    val navController = rememberNavController()
    TerminosScreen(navController)
}
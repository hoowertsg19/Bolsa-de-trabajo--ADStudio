package com.uam.bolsatrabajo.compose.homeParticipante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.R

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BolsaTrabajoTheme {
                val navController = rememberNavController()
                JobDetailScreen(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailScreen(navController: NavHostController) {
    var isExpanded by remember { mutableStateOf(false) }

    Scaffold(
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color(0xFF0099A8)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.go),
                            contentDescription = "Company Logo",
                            modifier = Modifier.size(80.dp),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
                        ) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF0F0F0))
                            .padding(vertical = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "PHP/JAVA Developer",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = montserrat,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Google • Managua • 1 day ago",
                                fontSize = 16.sp,
                                color = Color.Black,
                                fontFamily = montserrat
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Información de la vacante publicada",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = montserrat
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Descripción de Trabajo",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = montserrat
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isExpanded) {
                                "Buscamos un Desarrollador PHP/JAVA altamente motivado para unirse a nuestro equipo. El candidato ideal tendrá experiencia en el desarrollo y mantenimiento de aplicaciones web utilizando PHP y Java, con habilidades en front-end y bases de datos. Será responsable de escribir código limpio y eficiente, colaborar con equipos multidisciplinarios, y participar en la revisión de código. Se valorará experiencia con frameworks como Laravel o Spring y conocimiento en control de versiones (Git)."
                            } else {
                                "Buscamos un Desarrollador PHP/JAVA altamente motivado para unirse a nuestro equipo. El candidato ideal tendrá experiencia en el desarrollo y mantenimiento de aplicaciones web utilizando PHP y Java, con habilidades en front-end y bases de datos..."
                            },
                            fontFamily = montserrat
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(
                            onClick = { isExpanded = !isExpanded },
                            modifier = Modifier.align(Alignment.Start)
                        ) {
                            Text(if (isExpanded) "Leer menos" else "Leer más", color = Color(0xFF0099A8), fontFamily = montserrat)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Requisitos",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = montserrat
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Column {
                            Text("• Experiencia en PHP y Java.", fontFamily = montserrat)
                            Text("• Conocimiento en SQL y desarrollo front-end (HTML, CSS, JavaScript).", fontFamily = montserrat)
                            Text("• Capacidad para trabajar en equipo y comunicar ideas técnicas.", fontFamily = montserrat)
                            Text("• Experiencia con APIs RESTful y servicios web.", fontFamily = montserrat)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Ubicación",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = montserrat,
                            color = Color.Black
                        )
                        Text(
                            text = "Oficinas Google Managua, Bello Horizonte",
                            fontSize = 16.sp,
                            fontFamily = montserrat
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.m),
                            contentDescription = "Mapa de ubicación",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Informaciones",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = montserrat
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        InfoRow(label = "Posición", value = "Desarrollador Java")
                        InfoRow(label = "Salario", value = "10,000 Cordobas/M")
                        InfoRow(label = "Experiencia", value = "3 Años")
                        InfoRow(label = "Tipo de vacante", value = "Remoto")
                        InfoRow(label = "Tipo empleado", value = "Developer")
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate("UploadCvScreen") },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Text("APLICAR A VACANTE", color = Color.White, fontFamily = montserrat)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = montserrat
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontFamily = montserrat,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JobDetailScreenPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        JobDetailScreen(navController)
    }
}

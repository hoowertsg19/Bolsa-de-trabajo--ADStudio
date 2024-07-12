package com.uam.bolsatrabajo.compose.homeParticipante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
class ApplicationCompletedScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BolsaTrabajoTheme {
                val navController = rememberNavController()
                ApplicationCompletedScreenContent(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationCompletedScreenContent(navController: NavHostController) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
            ) {
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
                        .height(120.dp)
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
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF5F5F5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.p), // Reemplaza con tu ícono de PDF
                                contentDescription = "PDF Icon",
                                tint = Color.Red,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Hoowerts Gross - CV - Dev",
                                    fontSize = 16.sp,
                                    fontFamily = montserrat,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "867 Kb • 11 Jun 2024 a las 11:00 am",
                                    fontSize = 14.sp,
                                    fontFamily = montserrat,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(48.dp)) // Mayor separación entre la caja de PDF y la sección de "¡Envío exitoso!"
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.l2), // Reemplaza con tu ícono de éxito
                        contentDescription = "Success Icon",
                        modifier = Modifier.size(200.dp), // Aumentar el tamaño de la imagen
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "¡Envío exitoso!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = montserrat
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Felicitaciones, su postulación ha sido enviada.",
                        fontSize = 15.sp,
                        fontFamily = montserrat,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Empujar los botones hacia abajo
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /* TODO: Add action to find a similar job */ },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0F7FA)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(48.dp)
                    ) {
                        Text("ENCUENTRA UN TRABAJO SIMILAR", color = Color(0xFF0099A8), fontFamily = montserrat)
                    }
                    Button(
                        onClick = { /* TODO: Add action to go back to home */ },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 8.dp)
                            .width(200.dp)
                            .height(48.dp)
                    ) {
                        Text("VOLVER AL INICIO", color = Color.White, fontFamily = montserrat)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ApplicationCompletedScreenPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        ApplicationCompletedScreenContent(navController)
    }
}



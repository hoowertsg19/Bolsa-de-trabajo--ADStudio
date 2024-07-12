package com.uam.bolsatrabajo.compose.homeParticipante

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.*
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
class UploadCvScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BolsaTrabajoTheme {
                val navController = rememberNavController()
                JobApplicationScreen(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobApplicationScreen(navController: NavHostController) {
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri = uri
    }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
            ) {
                // Box Azul con icono de regreso y icono de más opciones y logo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) // Hacemos más pequeña la Box azul
                        .background(Color(0xFF0099A8)),
                    contentAlignment = Alignment.TopStart
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    IconButton(
                        onClick = { /*TODO: Add more options action*/ },
                        modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
                    ) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "More options", tint = Color.White)
                    }
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.go),
                            contentDescription = "Company Logo",
                            modifier = Modifier.size(80.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                // Box gris con el título y subtítulo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F0F0))
                        .padding(vertical = 16.dp) // Ajustamos el padding para repartir el espacio
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally // Alinea al centro
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "PHP/JAVA Developer",
                            fontSize = 20.sp, // Tamaño de fuente más pequeño
                            fontWeight = FontWeight.Bold,
                            fontFamily = montserrat,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Google • Managua • 1 day ago",
                            fontSize = 16.sp,
                            color = Color.Black, // Mismo color que "Google"
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
                        text = "Subir CV",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = montserrat
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Añade tu CV/Currículum vitae para solicitar un empleo",
                        fontSize = 13.sp,
                        fontFamily = montserrat,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.LightGray, thickness = 1.dp) // Divider para interlineado
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                            .background(Color(0xFFF5F5F5))
                            .clickable { filePickerLauncher.launch("*/*") }, // Habilitamos la funcionalidad de subir archivos
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.carpetilla), // Reemplaza con el nombre de tu icono
                                contentDescription = "Upload Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Subir CV/Currículum",
                                fontSize = 16.sp,
                                fontFamily = montserrat,
                                color = Color.Gray
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Información",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = montserrat
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp) // Hacemos más grande el cuadro de texto
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF5F5F5)),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Text(
                            text = "Explica por qué eres la persona adecuada para este trabajo.",
                            fontSize = 13.sp,
                            fontFamily = montserrat,
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f)) // Esto empuja el botón hacia la parte inferior
                Button(
                    onClick = { /*TODO: Add apply action*/ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp) // Centrado en la parte inferior
                        .height(48.dp)
                ) {
                    Text("APLICAR AHORA", color = Color.White, fontFamily = montserrat)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun JobApplicationScreenPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        JobApplicationScreen(navController)
    }
}

package com.uam.bolsatrabajo.compose.homeEmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de la Vacante", fontFamily = montserrat, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar")
                    }
                },
                actions = {
                    TextButton(onClick = { /* Acción para confirmar los detalles del trabajo */ }) {
                        Text("Guardar", color = Color(0xFF0099A8), fontFamily = montserrat, fontWeight = FontWeight.SemiBold)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .padding(16.dp)
        ) {
            JobDetailField("Posición*", "Añadir posición")
            JobDetailField("Tipo de vacante", "Añadir tipo de vacante")
            JobDetailField("Ubicacion", "Añadir ubicación")
            JobDetailField("Compañia", "Añadir compañía")
            JobDetailField("Tipo de empleado", "Añadir tipo de empleado")
            JobDetailField("Descripcion", "Añadir descripción")
        }
    }
}

@Composable
fun JobDetailField(label: String, placeholder: String) {
    val textState = rememberSaveable { mutableStateOf(TextFieldValue()) }
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(label, fontFamily = montserrat, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        BasicTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .padding(8.dp)
                        .background(Color(0xFFF0F0F0))
                        .fillMaxWidth()
                ) {
                    if (textState.value.text.isEmpty()) {
                        Text(placeholder, fontFamily = montserrat, fontSize = 14.sp, color = Color.Gray)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JobDetailsScreenPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        JobDetailsScreen(navController)
    }
}

package com.uam.bolsatrabajo.compose.homeEmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun JobConfirmationScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirmar Vacante", fontFamily = montserrat, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar")
                    }
                },
                actions = {
                    TextButton(onClick = { /* Acción para confirmar y publicar la vacante */ }) {
                        Text("Publicar", color = Color(0xFF0099A8), fontFamily = montserrat, fontWeight = FontWeight.SemiBold)
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
            Text("Confirmación de la Vacante", fontFamily = montserrat, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            JobDetailText("Posición", "Administrative Assistant")
            JobDetailText("Tipo de vacante", "Tiempo completo")
            JobDetailText("Ubicacion", "California, USA")
            JobDetailText("Compañia", "Soluciones Creativas S.A")
            JobDetailText("Tipo de empleado", "Full Time")
            JobDetailText("Descripcion", "Descripción del trabajo aquí...")
        }
    }
}

@Composable
fun JobDetailText(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(label, fontFamily = montserrat, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Text(value, fontFamily = montserrat, fontSize = 14.sp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun JobConfirmationScreenPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        JobConfirmationScreen(navController)
    }
}

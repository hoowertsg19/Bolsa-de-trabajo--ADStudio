package com.uam.bolsatrabajo.compose.homeEmp


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun AddJobScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Publicar Vacante", fontFamily = montserrat, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar")
                    }
                },
                actions = {
                    TextButton(onClick = { /* Acción para publicar vacante */ }) {
                        Text("Post", color = Color(0xFF0099A8), fontFamily = montserrat, fontWeight = FontWeight.SemiBold)
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
            JobField("Posición*", "Añadir posición") { /* Acción al click */ }
            JobField("Tipo de vacante", "Añadir tipo de vacante") { /* Acción al click */ }
            JobField("Ubicacion", "Añadir ubicación") { /* Acción al click */ }
            JobField("Compañia", "Añadir compañía") { /* Acción al click */ }
            JobField("Tipo de empleado", "Añadir tipo de empleado") { /* Acción al click */ }
            JobField("Descripcion", "Añadir descripción") { /* Acción al click */ }
        }
    }
}

@Composable
fun JobField(label: String, placeholder: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(label, fontFamily = montserrat, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(placeholder, fontFamily = montserrat, fontSize = 14.sp, color = Color.Gray)
        }
        Icon(Icons.Default.Add, contentDescription = "Añadir")
    }
}

@Preview(showBackground = true)
@Composable
fun AddJobScreenPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        AddJobScreen(navController)
    }
}

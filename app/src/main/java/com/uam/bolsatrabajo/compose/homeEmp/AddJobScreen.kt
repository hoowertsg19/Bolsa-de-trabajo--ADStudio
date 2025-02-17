package com.uam.bolsatrabajo.compose.homeEmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.R // Ensure this import is present


@Composable
fun AddJobScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color(0xFF111111))
            }
            TextButton(onClick = {  }) {
                Text("Publicar", color = Color(0xFF00A4B5), fontFamily = montserrat, fontWeight = FontWeight.SemiBold)
            }
        }


        Text(
            text = "Publicar Vacante",
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color(0xFF111111),
            modifier = Modifier.padding(start = 16.dp)
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            JobField("Posición*", "Añadir posición") { /* Acción al click */ }
            JobField("Tipo de vacante", "Añadir tipo de vacante") { /* Acción al click */ }
            JobField("Ubicación", "Añadir ubicación") { /* Acción al click */ }
            JobField("Compañía", "Añadir compañía") { /* Acción al click */ }
            JobField("Tipo de empleado", "Añadir tipo de empleado") { /* Acción al click */ }
            JobField("Descripción", "Añadir descripción") { /* Acción al click */ }
            JobField("Salario", "Añadir salario") { /* Acción al click */ }
        }
    }
}

@Composable
fun JobField(label: String, placeholder: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(label, fontFamily = montserrat, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF111111))
                Spacer(modifier = Modifier.height(4.dp))
                Text(placeholder, fontFamily = montserrat, fontSize = 14.sp, color = Color.Gray)
            }
            PlusButton(onClick)
        }
    }
}

@Composable
fun PlusButton(onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.anadir),
        contentDescription = "Añadir",
        modifier = Modifier
            .size(24.dp)
            .clickable(onClick = onClick)
            .clip(CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun AddJobScreenPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        AddJobScreen(navController)
    }
}


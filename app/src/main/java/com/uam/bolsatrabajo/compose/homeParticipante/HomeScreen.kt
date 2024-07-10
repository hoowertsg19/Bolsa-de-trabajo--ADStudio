package com.uam.bolsatrabajo.compose.homeParticipante

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.viewmodel.LoginViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: LoginViewModel = viewModel()) {
    val userName = viewModel.sessionManager.fetchUserName() ?: "Usuario" // Recupera el nombre del usuario

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hola, $userName",
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                    Log.d("Navigation", "Logging out and navigating to login_screen from home_screen")
                    viewModel.logout()
                    navController.navigate("login_screen") {
                        popUpTo(0) // Navegar al inicio del stack
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Cerrar Sesión")
            }
        }
    }
}

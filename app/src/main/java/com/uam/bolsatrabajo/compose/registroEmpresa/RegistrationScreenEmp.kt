package com.uam.bolsatrabajo.compose.registroEmpresa

import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.ui.theme.SemiTransparentBlue
import kotlinx.coroutines.delay

@Composable
fun RegistrationScreenEmp(navController: NavController) {
    val images = listOf(
        R.drawable.carrusel1,
        R.drawable.carrousel2,
        R.drawable.carrusel3
    )

    var currentImageIndex by remember { mutableIntStateOf(0) }
    val transitionState = remember { MutableTransitionState(false).apply { targetState = true } }

    LaunchedEffect(key1 = currentImageIndex) {
        delay(5000)  // Wait for 5 seconds
        transitionState.targetState = false
        delay(500)  // Duration of fade-out
        currentImageIndex = (currentImageIndex + 1) % images.size
        transitionState.targetState = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_principal),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .align(alignment = Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visibleState = transitionState,
                enter = fadeIn(animationSpec = tween(600, easing = FastOutSlowInEasing)),
                exit = fadeOut(animationSpec = tween(600, easing = FastOutSlowInEasing))
            ) {
                Image(
                    painter = painterResource(id = images[currentImageIndex]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .border(0.5.dp, Color.Black, CircleShape)
                        .clip(CircleShape)
                        .background(if (index == currentImageIndex) Color.Black else Color.White)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate("terms_and_conditions_screen")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Crea tu Cuenta", color = Color.White, fontSize = 16.sp, fontFamily = montserrat, fontWeight = FontWeight.SemiBold)
            }
            Button(
                onClick = {
                    navController.navigate("login_screen_emp")  // Navegar a la pantalla de inicio de sesión
                },
                colors = ButtonDefaults.buttonColors(containerColor = SemiTransparentBlue),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Iniciar sesión", color = Color(0xFF0099A8), fontSize = 16.sp, fontFamily = montserrat, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenEmpPreview() {
    BolsaTrabajoTheme {
        RegistrationScreenEmp(navController = rememberNavController())
    }
}

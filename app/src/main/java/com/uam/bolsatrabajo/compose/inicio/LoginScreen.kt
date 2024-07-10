package com.uam.bolsatrabajo.compose.inicio

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.ui.theme.SemiTransparentBlue
import kotlinx.coroutines.delay
import androidx.compose.runtime.livedata.observeAsState
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.lootie.InicioSesionLoad
import com.uam.bolsatrabajo.ui.theme.Montserrat
import com.uam.bolsatrabajo.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    onForgotPasswordClick: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val images = listOf(
        R.drawable.login1,
        R.drawable.login2,
        R.drawable.carrusel1
    )

    var currentImageIndex by remember { mutableStateOf(0) }
    val transitionState = remember { MutableTransitionState(false).apply { targetState = true } }

    LaunchedEffect(key1 = currentImageIndex) {
        delay(5000)
        transitionState.targetState = false
        delay(500)
        currentImageIndex = (currentImageIndex + 1) % images.size
        transitionState.targetState = true
    }

    val loginResult by viewModel.loginResult.observeAsState()
    var cif by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cifError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var showLoadingAnimation by remember { mutableStateOf(false) }
    var loginErrorMessage by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(SemiTransparentBlue)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(SemiTransparentBlue)
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
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxWidth()
                                .height(300.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(SemiTransparentBlue)
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .width(400.dp)
                            .align(Alignment.Center)
                            .padding(16.dp)
                            .height(510.dp)
                            .shadow(0.00005.dp, RoundedCornerShape(16.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "¡Bienvenido!",
                            fontSize = 35.sp,
                            fontFamily = montserrat,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF0099A8),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.uamletras),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        OutlinedTextField(
                            value = cif,
                            onValueChange = {
                                cif = it
                                cifError = it.isBlank()
                            },
                            label = { Text("CIF") },
                            isError = cifError,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, errorContainerColor = Color.White),
                            modifier = Modifier
                                .width(300.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        if (cifError) {
                            Text(
                                text = "CIF no válido",
                                color = Color.Red,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                passwordError = it.isBlank()
                            },
                            label = { Text("Contraseña") },
                            isError = passwordError,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ), colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, errorContainerColor = Color.White),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    Icons.Default.Visibility
                                else Icons.Default.VisibilityOff

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, contentDescription = null)
                                }
                            },
                            modifier = Modifier
                                .width(300.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        if (passwordError) {
                            Text(
                                text = "Contraseña incorrecta",
                                color = Color.Red,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        if (loginErrorMessage.isNotEmpty()) {
                            Text(
                                text = loginErrorMessage,
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                        Button(
                            onClick = {
                                cifError = cif.isBlank()
                                passwordError = password.isBlank()
                                if (!cifError && !passwordError) {
                                    showLoadingAnimation = true
                                    viewModel.login(cif, password)
                                }
                            },
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                            modifier = Modifier
                                .width(180.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "Ingresar",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        TextButton(
                            onClick = onForgotPasswordClick,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "¿Olvidó su contraseña?", color = Color(0xFF0099A8))
                        }
                    }
                }
            }
        }
    )

    loginResult?.let {
        if (it.success) {
            showLoadingAnimation = false
            Log.d("Navigation", "Login successful, navigating to home_screen")
            navController.navigate("home_screen") {
                popUpTo("login_screen") { inclusive = true }
            }
        } else {
            showLoadingAnimation = false
            loginErrorMessage = it.message
            Log.e("Login", "Login failed: ${it.message}")
        }
    }

    if (showLoadingAnimation) {
        InicioSesionLoad()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    BolsaTrabajoTheme {
        LoginScreen(navController = rememberNavController(), onForgotPasswordClick = {})
    }
}

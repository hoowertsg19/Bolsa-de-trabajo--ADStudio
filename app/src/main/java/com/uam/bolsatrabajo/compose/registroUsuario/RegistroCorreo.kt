package com.uam.bolsatrabajo.compose.registroUsuario

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.lootie.InicioSesionLoad
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.viewmodel.RegistroEmpViewModel
import com.uam.bolsatrabajo.viewmodel.RegistroViewModel

@Composable
fun RegistroCorreo(
    navController: NavHostController,
    viewModel: RegistroViewModel,
    onCloseClick: () -> Unit
) {
    var correoElectronico by remember { mutableStateOf(viewModel.correoElectronico) }
    var emailError by remember { mutableStateOf(false) }
    var showPasswordField by remember { mutableStateOf(false) }
    var contrasena by remember { mutableStateOf(viewModel.contrasena) }
    var passwordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val isLoading = remember { mutableStateOf(false) }
    val registroResult by viewModel.registroResult.observeAsState()

    LaunchedEffect(registroResult) {
        if (registroResult == "Registro exitoso") {
            navController.navigate("registro_exitoso") {
                popUpTo("registro_correo") { inclusive = true }
            }
        } else if (registroResult?.startsWith("Error") == true) {
            navController.navigate("registro_error") {
                popUpTo("registro_correo") { inclusive = true }
            }
        }
    }

    if (isLoading.value) {
        InicioSesionLoad()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.logo_principal),
                    contentDescription = null,
                    modifier = Modifier
                        .height(125.dp)
                        .align(Alignment.TopStart)
                )
                IconButton(
                    onClick = onCloseClick,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Cerrar",
                        tint = Color(0xFF0099A8),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Text(
                text = "Agrega tu correo electrónico",
                fontSize = 32.sp,
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF0099A8),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = correoElectronico,
                    onValueChange = {
                        correoElectronico = it
                        emailError = it.isBlank() || !PatternsCompat.EMAIL_ADDRESS.matcher(it).matches()
                        showPasswordField = !emailError && it.isNotBlank()
                    },
                    label = { Text("Email") },
                    isError = emailError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ), colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.LightGray),
                    modifier = Modifier.fillMaxWidth()
                )
                if (emailError) {
                    Text(
                        text = "El campo de correo electrónico está vacío o no es válido. Por favor, asegúrate de ingresarlo correctamente.",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (showPasswordField) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = contrasena,
                        onValueChange = { contrasena = it },
                        label = { Text("Contraseña") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = null)
                            }
                        }, colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.LightGray),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "¡Mínimo de 8 caracteres!",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    if (!emailError && correoElectronico.isNotBlank() && contrasena.isNotBlank()) {
                        isLoading.value = true
                        viewModel.correoElectronico = correoElectronico
                        viewModel.contrasena = contrasena
                        Log.d("RegistroCorreo", "Datos ingresados - Email: $correoElectronico, Password: $contrasena")
                        viewModel.registrarUsuario()
                    }
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Continuar", color = Color.White, fontFamily = montserrat, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
        }
    }
}

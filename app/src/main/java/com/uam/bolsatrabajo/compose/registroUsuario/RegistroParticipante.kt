package com.uam.bolsatrabajo.compose.registroUsuario

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.viewmodel.RegistroEmpViewModel
import com.uam.bolsatrabajo.viewmodel.RegistroViewModel


@Composable
fun RegistroParticipante(navController: NavHostController, viewModel: RegistroViewModel, onCloseClick: () -> Unit) {
    var nombre by remember { mutableStateOf(viewModel.nombre) }
    var apellido by remember { mutableStateOf(viewModel.apellido) }
    var nombreError by remember { mutableStateOf(false) }
    var apellidoError by remember { mutableStateOf(false) }
    var tipoUsuario by remember { mutableStateOf(viewModel.tipoUsuario) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
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
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Registra tu Nombre",
            fontSize = 32.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0099A8),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    nombreError = it.isBlank()
                },
                label = { Text("Nombre") },
                isError = nombreError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down) }
                ), colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.LightGray),
                modifier = Modifier.fillMaxWidth()
            )
            if (nombreError) {
                Text(
                    text = "Por favor, escribe tu nombre",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = apellido,
                onValueChange = {
                    apellido = it
                    apellidoError = it.isBlank()
                },
                label = { Text("Apellido") },
                isError = apellidoError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ), colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.LightGray),
                modifier = Modifier.fillMaxWidth()
            )
            if (apellidoError) {
                Text(
                    text = "Por favor, escribe tu apellido",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selecciona tu tipo de usuario:",
            fontSize = 16.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0099A8),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = tipoUsuario == "Estudiante",
                onClick = { tipoUsuario = "Estudiante" },
                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF0099A8))
            )
            Text(text = "Estudiante", fontFamily = montserrat)

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = tipoUsuario == "Egresado",
                onClick = { tipoUsuario = "Egresado" },
                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF0099A8))
            )
            Text(text = "Egresado", fontFamily = montserrat)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                nombreError = nombre.isBlank()
                apellidoError = apellido.isBlank()
                if (!nombreError && !apellidoError) {
                    Log.d("RegistroParticipante", "Datos ingresados - Nombre: $nombre, Apellido: $apellido, TipoUsuario: $tipoUsuario")
                    viewModel.nombre = nombre
                    viewModel.apellido = apellido
                    viewModel.tipoUsuario = tipoUsuario
                    navController.navigate("registro_cif")
                }
            },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Continuar",
                color = Color.White,
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegistroParticipantePreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        val viewModel = RegistroViewModel()
        RegistroParticipante(navController = navController, viewModel = viewModel, onCloseClick = {})
    }
}

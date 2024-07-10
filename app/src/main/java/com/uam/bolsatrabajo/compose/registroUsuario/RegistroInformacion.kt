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
import com.google.relay.compose.RowScopeInstanceImpl.align
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.viewmodel.RegistroEmpViewModel
import com.uam.bolsatrabajo.viewmodel.RegistroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroInformacion(navController: NavHostController, viewModel: RegistroViewModel, onCloseClick: () -> Unit) {
    var carrera by remember { mutableStateOf(viewModel.carrera) }
    var anioGraduacion by remember { mutableStateOf(viewModel.anioGraduacion) }
    var sexo by remember { mutableStateOf(viewModel.sexo) }
    var expanded by remember { mutableStateOf(false) }
    var carreraError by remember { mutableStateOf(false) }
    var anioGraduacionError by remember { mutableStateOf(false) }
    val anios = (2010..2035).toList()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Agrega tu Información",
            fontSize = 42.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0099A8),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = carrera,
                onValueChange = {
                    carrera = it
                    carreraError = it.isBlank()
                },
                label = { Text("Carrera") },
                isError = carreraError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down) }
                ), colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.LightGray),
                modifier = Modifier.fillMaxWidth()
            )
            if (carreraError) {
                Text(
                    text = "El campo de carrera está vacío. Por favor, elige tu carrera.",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = anioGraduacion,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Año de Graduación") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }, colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.LightGray),
                    isError = anioGraduacionError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor() // Asegura que el menú esté anclado al campo de texto
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    anios.forEach { anio ->
                        DropdownMenuItem(
                            text = { Text(text = anio.toString(), color = Color.Black) },
                            onClick = {
                                anioGraduacion = anio.toString()
                                expanded = false
                            }
                        )
                    }
                }
            }

            if (anioGraduacionError) {
                Text(
                    text = "El campo está vacío. Por favor, elige tu año.",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Si eres estudiante, indica el año previsto.",
                fontSize = 12.sp,
                fontFamily = montserrat,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Sexo",
                fontSize = 16.sp,
                fontFamily = montserrat,
                color = Color(0xFF0099A8),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()) {
                ToggleButton(
                    text = "Hombre",
                    selected = sexo == "Hombre",
                    onClick = { sexo = "Hombre" }
                )
                Spacer(modifier = Modifier.width(8.dp))
                ToggleButton(
                    text = "Mujer",
                    selected = sexo == "Mujer",
                    onClick = { sexo = "Mujer" }
                )
                Spacer(modifier = Modifier.width(8.dp))
                ToggleButton(
                    text = "Indefinido",
                    selected = sexo == "Indefinido",
                    onClick = { sexo = "Indefinido" }
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                carreraError = carrera.isBlank()
                anioGraduacionError = anioGraduacion.isBlank()
                if (!carreraError && !anioGraduacionError) {
                    Log.d("RegistroInformacion", "Datos ingresados - Carrera: $carrera, AnioGraduacion: $anioGraduacion, Sexo: $sexo")
                    viewModel.carrera = carrera
                    viewModel.anioGraduacion = anioGraduacion
                    viewModel.sexo = sexo
                    navController.navigate("registro_correo")
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

@Composable
fun ToggleButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color.Black else Color.LightGray,
            contentColor = if (selected) Color.White else Color.Black
        ),
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .defaultMinSize(minHeight = 36.dp)
            .align(Alignment.CenterVertically)
    ) {
        Text(text = text, fontFamily = montserrat, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroInformacionPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        val viewModel = RegistroViewModel()
        RegistroInformacion(navController = navController, viewModel = viewModel, onCloseClick = {})
    }
}

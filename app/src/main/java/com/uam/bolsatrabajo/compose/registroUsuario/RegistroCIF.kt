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
import androidx.compose.ui.text.input.KeyboardType
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
fun RegistroCIF(navController: NavHostController, viewModel: RegistroViewModel, onCloseClick: () -> Unit) {
    var cif by remember { mutableStateOf(viewModel.cif) }
    var cifError by remember { mutableStateOf(false) }
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
            text = "Ingresa tu CIF",
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
                value = cif,
                onValueChange = {
                    cif = it
                    cifError = it.isBlank()
                },
                label = { Text("CIF", color = MaterialTheme.colorScheme.onSurface) },
                isError = cifError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ) ,colors = TextFieldDefaults.colors(Color.Black, focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.LightGray),
                modifier = Modifier.fillMaxWidth()
            )
            if (cifError) {
                Text(
                    text = "El campo de CIF está vacío. Por favor, asegúrate de ingresarlo.",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                cifError = cif.isBlank()
                if (!cifError) {
                    Log.d("RegistroCIF", "Datos ingresados - CIF: $cif")
                    viewModel.cif = cif
                    navController.navigate("registro_informacion")
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
fun RegistroCIFPreview() {
    BolsaTrabajoTheme {
        val navController = rememberNavController()
        val viewModel = RegistroViewModel()
        RegistroCIF(navController = navController, viewModel = viewModel, onCloseClick = {})
    }
}

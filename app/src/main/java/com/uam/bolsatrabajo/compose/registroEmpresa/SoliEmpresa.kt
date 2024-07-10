package com.uam.bolsatrabajo.compose.registroEmpresa

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DropdownMenu
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DropdownMenuItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.Icons
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.filled.ArrowDropDown
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import com.uam.bolsatrabajo.model.Empresa
import com.uam.bolsatrabajo.viewmodel.RegistroEmpViewModel

@Composable
fun SoliEmpresa(navController: NavController, viewModel: RegistroEmpViewModel) {
    var companyName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf("Nicaragua") }
    var countryMenuExpanded by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var phoneNumberError by remember { mutableStateOf(false) }

    val countries = listOf("Nicaragua", "Costa Rica", "El Salvador", "Guatemala", "Honduras", "Panamá", "España", "Estados Unidos")
    val countryFlags = listOf(
        R.drawable.nicaragua, R.drawable.costa_rica, R.drawable.el_salvador, R.drawable.guatemala,
        R.drawable.honduras, R.drawable.panama, R.drawable.spain, R.drawable.usa
    )
    val countryPrefixes = listOf("+505", "+506", "+503", "+502", "+504", "+507", "+34", "+1")

    val selectedCountryPrefix = getCountryPrefix(selectedCountry, countryPrefixes, countries)

    fun validateForm() {
        showError = companyName.isBlank() || email.isBlank() || phoneNumber.isBlank() || location.isBlank()
        emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        phoneNumberError = !validatePhoneNumber(phoneNumber, selectedCountry)
    }

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
                onClick = { navController.popBackStack() },
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
            text = "Regístrate ahora",
            fontSize = 38.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            color = Color(0xFF0099A8),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = companyName,
            onValueChange = {
                companyName = it
                validateForm()
            },
            label = { Text("Nombre de la Empresa") },
            isError = showError && companyName.isBlank(),
            colors = TextFieldDefaults.colors(
                Color.Black, focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (showError && companyName.isBlank()) {
            Text(
                text = "El campo de nombre de la empresa está vacío.",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                validateForm()
            },
            label = { Text("Correo electrónico") },
            isError = showError && (email.isBlank() || emailError),
            colors = TextFieldDefaults.colors(
                Color.Black, focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (showError && email.isBlank()) {
            Text(
                text = "El campo de correo electrónico está vacío.",
                color = Color.Red,
                fontSize = 12.sp
            )
        } else if (showError && emailError) {
            Text(
                text = "El formato del correo electrónico es incorrecto.",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .height(48.dp)
                    .align(Alignment.CenterVertically)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("País") },
                    trailingIcon = {
                        IconButton(onClick = { countryMenuExpanded = !countryMenuExpanded }) {
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Expandir")
                        }
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = countryFlags[countries.indexOf(selectedCountry)]),
                            contentDescription = "Bandera del País",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(35.dp),
                            contentScale = ContentScale.Fit
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        Color.Black, focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.Black
                    ),
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 8.dp)
                )
                DropdownMenu(
                    expanded = countryMenuExpanded,
                    onDismissRequest = { countryMenuExpanded = false }
                ) {
                    countries.forEachIndexed { index, country ->
                        DropdownMenuItem(
                            onClick = {
                                selectedCountry = country
                                countryMenuExpanded = false
                                phoneNumber = ""  // Reset phone number when country changes
                                validateForm()
                            }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = countryFlags[index]),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = country)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(4.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.all { char -> char.isDigit() || char == '-' || char == '(' || char == ')' || char == ' ' }) {
                        val cleanedNumber = it.filter { char -> char.isDigit() }
                        if (cleanedNumber.length <= getMaxPhoneNumberLength(selectedCountry)) {
                            phoneNumber = formatPhoneNumber(cleanedNumber, selectedCountry)
                            validateForm()
                        }
                    }
                },
                label = { Text("Teléfono de Contacto") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                isError = showError && (phoneNumber.isBlank() || phoneNumberError),
                modifier = Modifier.weight(2f),
                leadingIcon = {
                    Text(
                        text = selectedCountryPrefix,
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },
                colors = TextFieldDefaults.colors(
                    Color.Black, focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.Black
                ),
            )
        }
        if (showError && phoneNumber.isBlank()) {
            Text(
                text = "El campo de teléfono de contacto está vacío.",
                color = Color.Red,
                fontSize = 12.sp
            )
        } else if (showError && phoneNumberError) {
            Text(
                text = "El número de teléfono no es válido para el país seleccionado.",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = location,
            onValueChange = {
                location = it
                validateForm()
            },
            label = { Text("Ubicación") },
            isError = showError && location.isBlank(),
            colors = TextFieldDefaults.colors(
                Color.Black, focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White, errorContainerColor = Color.White, unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (showError && location.isBlank()) {
            Text(
                text = "El campo de ubicación está vacío.",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                validateForm()
                if (!showError && !emailError && !phoneNumberError) {
                    viewModel.nombreEmpresa.value = companyName
                    viewModel.correoElectronico.value = email
                    viewModel.telefonoContacto.value = selectedCountryPrefix + phoneNumber
                    viewModel.ubicacion.value = location
                    viewModel.registrarEmpresa()
                    navController.navigate("pacman_screen")
                    navController.navigate("registro_exitoso_empresa")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Continuar",
                color = Color.White,
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun getCountryPrefix(selectedCountry: String, countryPrefixes: List<String>, countries: List<String>): String {
    val index = countries.indexOf(selectedCountry)
    return if (index >= 0) countryPrefixes[index] else ""
}

fun validatePhoneNumber(phoneNumber: String, country: String): Boolean {
    val cleanedNumber = phoneNumber.filter { it.isDigit() }
    return when (country) {
        "Nicaragua", "Costa Rica", "El Salvador", "Guatemala", "Honduras", "Panamá" -> cleanedNumber.length == 8
        "España" -> cleanedNumber.length in 9..10
        "Estados Unidos" -> cleanedNumber.length == 10 || (cleanedNumber.length == 11 && cleanedNumber.startsWith("1"))
        else -> false
    }
}

fun getMaxPhoneNumberLength(country: String): Int {
    return when (country) {
        "Nicaragua", "Costa Rica", "El Salvador", "Guatemala", "Honduras", "Panamá" -> 8
        "España" -> 10
        "Estados Unidos" -> 10
        else -> 8
    }
}

fun formatPhoneNumber(phoneNumber: String, country: String): String {
    return when (country) {
        "Nicaragua" -> formatNicaraguaNumber(phoneNumber)
        "Costa Rica" -> formatCostaRicaNumber(phoneNumber)
        "El Salvador" -> formatElSalvadorNumber(phoneNumber)
        "Guatemala" -> formatGuatemalaNumber(phoneNumber)
        "Honduras" -> formatHondurasNumber(phoneNumber)
        "Panamá" -> formatPanamaNumber(phoneNumber)
        "España" -> formatSpainNumber(phoneNumber)
        "Estados Unidos" -> formatUSANumber(phoneNumber)
        else -> phoneNumber
    }
}

fun formatNicaraguaNumber(number: String): String {
    return if (number.length == 8) "${number.substring(0, 4)}-${number.substring(4)}" else number
}

fun formatCostaRicaNumber(number: String): String {
    return if (number.length == 8) "${number.substring(0, 4)}-${number.substring(4)}" else number
}

fun formatElSalvadorNumber(number: String): String {
    return if (number.length == 8) "${number.substring(0, 4)}-${number.substring(4)}" else number
}

fun formatGuatemalaNumber(number: String): String {
    return if (number.length == 8) "${number.substring(0, 4)}-${number.substring(4)}" else number
}

fun formatHondurasNumber(number: String): String {
    return if (number.length == 8) "${number.substring(0, 4)}-${number.substring(4)}" else number
}

fun formatPanamaNumber(number: String): String {
    return if (number.length == 8) "${number.substring(0, 4)}-${number.substring(4)}" else number
}

fun formatSpainNumber(number: String): String {
    return when (number.length) {
        9 -> "${number.substring(0, 3)} ${number.substring(3, 6)} ${number.substring(6)}"
        10 -> "${number.substring(0, 3)} ${number.substring(3, 6)} ${number.substring(6)}"
        else -> number
    }
}

fun formatUSANumber(number: String): String {
    return when {
        number.length == 10 -> "(${number.substring(0, 3)}) ${number.substring(3, 6)}-${number.substring(6)}"
        number.length == 11 && number.startsWith("1") -> "(${number.substring(1, 4)}) ${number.substring(4, 7)}-${number.substring(7)}"
        else -> number
    }
}

@Preview(
    showBackground = true,
    widthDp = 393, // Width in dp (approximately the width of 1080 pixels at 2.75 dp per pixel)
    heightDp = 820  // Height in dp (approximately the height of 2400 pixels at 2.75 dp per pixel)
)
@Composable
fun PreviewSoliEmpresa() {
    val navController = rememberNavController()
    val viewModel = RegistroEmpViewModel()
    SoliEmpresa(navController, viewModel)
}

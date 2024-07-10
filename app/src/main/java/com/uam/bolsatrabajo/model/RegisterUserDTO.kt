package com.uam.bolsatrabajo.model

data class RegisterUserDTO(
    val nombre: String,
    val apellido: String,
    val correoElectronico: String,
    val contrasena: String,
    val tipoUsuario: String,
    val cif: String,
    val carrera: String,
    val anioGraduacion: Int?,
    val sexo: String
)

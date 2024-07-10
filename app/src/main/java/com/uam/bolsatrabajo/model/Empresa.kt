package com.uam.bolsatrabajo.model

data class Empresa(
    val id: Long? = null,
    val nombre: String,
    val correoElectronico: String,
    val telefonoContacto: String,
    val ubicacion: String,
    val aprobada: Boolean = false
)

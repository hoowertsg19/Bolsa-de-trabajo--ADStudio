package com.uam.bolsatrabajo.model

data class Vacante(
    val id: Long,
    val posicion: String,
    val tipoVacante: String,
    val ubicacion: String,
    val tipoEmpleado: String,
    val descripcion: String,
    val autorizado: Boolean,
    val activa: Boolean,
    val fechaPublicacion: String,
    val salario: Double,
    val empresa: Empresa?, // Incluye la referencia a la empresa si es necesario
    val postulaciones: List<Postulacion>?
)
